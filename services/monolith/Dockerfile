FROM alpine:latest AS base

RUN apk --no-cache add openjdk21-jdk openjdk21-jmods binutils zip

FROM base AS builder

WORKDIR /builder

ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar otel-agent.jar

ENV JAVA_MINIMAL="/opt/java-minimal"
ENV JAVA_HOME="/usr/lib/jvm/java-21-openjdk"

COPY services/monolith/build/libs/monolith.jar tmp/app.jar

RUN mkdir /opt/tmp

RUN jar -xf tmp/app.jar

RUN jdeps --ignore-missing-deps -q \
    --recursive \
    --multi-release 21 \
    --print-module-deps \
    --class-path 'BOOT-INF/lib/*' \
    tmp/app.jar > deps.info

RUN jlink \
    --add-modules $(cat deps.info) \
    --strip-debug \
    --compress 2 \
    --no-header-files \
    --no-man-pages \
    --output "$JAVA_MINIMAL"

RUN java -Djarmode=tools -jar tmp/app.jar extract --layers --destination extracted

RUN ldd /opt/java-minimal/bin/java \
    | grep '=>' \
    | awk '{print $3}' \
    | sort -u -o /tmp/libs.txt

RUN xargs -a /tmp/libs.txt -I '{}' sh -c 'mkdir -p /lib-deps$(dirname {}) && cp -v {} /lib-deps{}'

FROM scratch

ENV JAVA_HOME=/opt/java-minimal
ENV PATH="$JAVA_HOME/bin"

COPY --from=builder "$JAVA_HOME" "$JAVA_HOME"

COPY --from=builder /opt/tmp /tmp
COPY --from=builder /lib-deps /

COPY config config
COPY --from=builder /builder/otel-agent.jar otel-agent.jar
COPY --from=builder /builder/extracted/dependencies/ ./
COPY --from=builder /builder/extracted/spring-boot-loader/ ./
COPY --from=builder /builder/extracted/snapshot-dependencies/ ./
COPY --from=builder /builder/extracted/application/ ./

EXPOSE 8080

ENTRYPOINT ["/opt/java-minimal/bin/java"]
CMD ["-javaagent:/app/otel-agent.jar" ,"-XX:+UseZGC", "-XX:+ZGenerational", "-XX:MaxRAMPercentage=85.0", "-XX:+UseContainerSupport", "-jar", "app.jar"]
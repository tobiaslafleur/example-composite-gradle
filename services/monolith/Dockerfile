FROM alpine:latest AS base

RUN apk --no-cache add openjdk21-jdk openjdk21-jmods binutils zip

FROM base AS builder

WORKDIR /builder

ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar otel-agent.jar

ENV JAVA_MINIMAL="/opt/java-minimal"
ENV JAVA_HOME="/usr/lib/jvm/java-21-openjdk"

COPY services/monolith/build/libs/monolith.jar tmp/app.jar

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

FROM alpine:latest

RUN adduser -D -u 1001 nonroot

WORKDIR /app

ENV JAVA_HOME=/opt/java-minimal
ENV PATH="$JAVA_HOME/bin:$PATH"
ENV JAVA_OPTS="-javaagent:/app/otel-agent.jar -XX:+UseZGC -XX:+ZGenerational -XX:MaxRAMPercentage=75.0 -XX:+UseContainerSupport"

COPY --from=builder "$JAVA_HOME" "$JAVA_HOME"

COPY --chown=nonroot:nonroot config config
COPY --from=builder --chown=nonroot:nonroot /builder/otel-agent.jar otel-agent.jar
COPY --from=builder --chown=nonroot:nonroot /builder/extracted/dependencies/ ./
COPY --from=builder --chown=nonroot:nonroot /builder/extracted/spring-boot-loader/ ./
COPY --from=builder --chown=nonroot:nonroot /builder/extracted/snapshot-dependencies/ ./
COPY --from=builder --chown=nonroot:nonroot /builder/extracted/application/ ./

USER nonroot

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar app.jar"]
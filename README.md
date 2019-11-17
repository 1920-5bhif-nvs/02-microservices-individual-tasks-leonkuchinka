# 02-microservices-individual-tasks-leonkuchinka
### Extensions hinzufügen

```
mvn quarkus:add-extension -Dextensions="health"
mvn quarkus:add-extension -Dextensions="metrics"
```
#### Ergebnis in pom.xml:
```
<dependency>
    <groupId>io.quarkus</groupId>
    <artifactId>quarkus-smallrye-metrics</artifactId>
</dependency>
<dependency>
    <groupId>io.quarkus</groupId>
    <artifactId>quarkus-smallrye-health</artifactId>
</dependency>
```

### Ports konfigurieren:
```
quarkus.http.port=8080
%dev.quarkus.http.port=8181
```

### Metrics:
 - @Counted
 - @Timed
 - @Gauge

```
curl -H"Acept: application/json" localhost:8181/metrics/application
```

### Health:
```
curl -H"Acept: application/json" http://localhost:8181/health
```
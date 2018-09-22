# Demo de Alta Disponibilidad con Spring Boot

Presenta como implementar de una manera sencilla un balanceador de carga 
y compartiendo las sesiones vía Data Grid en memoria.

**Proyecto que utiliza:**
* Spring Boot.
* Spring Session.
* Hazelcast.
* HaProxy.

**La Configuración de en HAproxy:**

Incluir al final del archivo **/etc/haproxy/haproxy.cfg** 
 
 ``` sh
 listen aplicacion_web
    bind *:80 
      mode http
      stats enable
      stats uri /stats
      stats realm HAProxy\ Statistics
      stats auth cda:cda
      balance roundrobin
      server app-01 localhost:8080 check maxconn 4000 fall 3 cookie L1 #Para validar la conexion con los puntos.
      server app-02 localhost:8081 check maxconn 4000 fall 3 cookie L2 #Para validar la conexión con los puntos.
      server app-03 localhost:8082 check maxconn 4000 fall 3 cookie L3 #Para validar la conexión con los puntos.
 ```

**Para Correr el proyecto:**

Las instancias deben estar apuntando en los puertos 8080, 8081 y 8082 y HAProxy en el puerto 80.
      
# LittleShop

## Content
* [General info](#general-info)
* [Demonstration](#demonstration)
* [Technologies](#technologies)
* [Features](#features)
* [Setup](#setup)
---

```
 :: Spring Boot ::                (v3.2.5)

2025-09-05T17:18:05.750+02:00  INFO 16492 --- [Leron] [           main] org.leron.LeronApplication               : Starting LeronApplication using Java 23.0.1 with PID 16492 (C:\Users\vdoro\IdeaProjects\Leron\target\classes started by vdoro in C:\Users\vdoro\IdeaProjects\Leron)
2025-09-05T17:18:05.752+02:00  INFO 16492 --- [Leron] [           main] org.leron.LeronApplication               : No active profile set, falling back to 1 default profile: "default"
2025-09-05T17:18:06.302+02:00  INFO 16492 --- [Leron] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2025-09-05T17:18:06.341+02:00  INFO 16492 --- [Leron] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 33 ms. Found 3 JPA repository interfaces.
2025-09-05T17:18:06.692+02:00  INFO 16492 --- [Leron] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2025-09-05T17:18:06.700+02:00  INFO 16492 --- [Leron] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2025-09-05T17:18:06.700+02:00  INFO 16492 --- [Leron] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.20]
2025-09-05T17:18:06.736+02:00  INFO 16492 --- [Leron] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2025-09-05T17:18:06.736+02:00  INFO 16492 --- [Leron] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 943 ms
2025-09-05T17:18:06.826+02:00  INFO 16492 --- [Leron] [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2025-09-05T17:18:06.866+02:00  INFO 16492 --- [Leron] [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.4.4.Final
2025-09-05T17:18:06.890+02:00  INFO 16492 --- [Leron] [           main] o.h.c.internal.RegionFactoryInitiator    : HHH000026: Second-level cache disabled
2025-09-05T17:18:07.040+02:00  INFO 16492 --- [Leron] [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2025-09-05T17:18:07.056+02:00  INFO 16492 --- [Leron] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2025-09-05T17:18:07.200+02:00  INFO 16492 --- [Leron] [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection org.postgresql.jdbc.PgConnection@718989fa
2025-09-05T17:18:07.201+02:00  INFO 16492 --- [Leron] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2025-09-05T17:18:07.246+02:00  WARN 16492 --- [Leron] [           main] org.hibernate.orm.deprecation            : HHH90000025: PostgreSQLDialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
2025-09-05T17:18:07.858+02:00  INFO 16492 --- [Leron] [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2025-09-05T17:18:07.941+02:00  INFO 16492 --- [Leron] [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2025-09-05T17:18:08.167+02:00  WARN 16492 --- [Leron] [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2025-09-05T17:18:08.294+02:00  INFO 16492 --- [Leron] [           main] o.s.s.web.DefaultSecurityFilterChain     : Will secure any request with [org.springframework.security.web.session.DisableEncodeUrlFilter@5e643402, org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@ff12b53, org.springframework.security.web.context.SecurityContextHolderFilter@52a0bc48, org.springframework.security.web.header.HeaderWriterFilter@8de4206, org.springframework.web.filter.CorsFilter@78141c58, org.springframework.security.web.authentication.logout.LogoutFilter@4fc8163, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter@5f7bd970, org.springframework.security.web.savedrequest.RequestCacheAwareFilter@24ce5d4c, org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@24fc9aa5, org.springframework.security.web.authentication.AnonymousAuthenticationFilter@9863ed5, org.springframework.security.web.access.ExceptionTranslationFilter@5542418c, org.springframework.security.web.access.intercept.AuthorizationFilter@4fc84c92]
2025-09-05T17:18:08.498+02:00  INFO 16492 --- [Leron] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
2025-09-05T17:18:08.503+02:00  INFO 16492 --- [Leron] [           main] org.leron.LeronApplication               : Started LeronApplication in 3.009 seconds (process running for 5.323)
Hibernate: select r1_0.id,r1_0.name from roles r1_0 where r1_0.name=?
Hibernate: select r1_0.id,r1_0.name from roles r1_0 where r1_0.name=?
Hibernate: select u1_0.id,u1_0.email,u1_0.password,u1_0.registration_date,u1_0.username from users u1_0 where u1_0.username=?
Hibernate: select r1_0.user_id,r1_1.id,r1_1.name from user_roles r1_0 join roles r1_1 on r1_1.id=r1_0.role_id where r1_0.user_id=?
Default roles and admin user checked/created.
Hibernate: select r1_0.id,r1_0.name from roles r1_0 where r1_0.name=?
Hibernate: select r1_0.id,r1_0.name from roles r1_0 where r1_0.name=?
Hibernate: select u1_0.id,u1_0.email,u1_0.password,u1_0.registration_date,u1_0.username from users u1_0 where u1_0.username=?
Hibernate: select r1_0.user_id,r1_1.id,r1_1.name from user_roles r1_0 join roles r1_1 on r1_1.id=r1_0.role_id where r1_0.user_id=?
Hibernate: select u1_0.id,u1_0.email,u1_0.password,u1_0.registration_date,u1_0.username from users u1_0 where u1_0.username=?
Hibernate: select r1_0.user_id,r1_1.id,r1_1.name from user_roles r1_0 join roles r1_1 on r1_1.id=r1_0.role_id where r1_0.user_id=?
Hibernate: select count(*) from products p1_0
2025-09-05T17:19:13.305+02:00  INFO 16492 --- [Leron] [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2025-09-05T17:19:13.305+02:00  INFO 16492 --- [Leron] [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2025-09-05T17:19:13.306+02:00  INFO 16492 --- [Leron] [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms
Hibernate: select u1_0.id,u1_0.email,u1_0.password,u1_0.registration_date,u1_0.username from users u1_0 where u1_0.username=?
Hibernate: select r1_0.user_id,r1_1.id,r1_1.name from user_roles r1_0 join roles r1_1 on r1_1.id=r1_0.role_id where r1_0.user_id=?
Hibernate: select p1_0.id,p1_0.description,p1_0.image_url,p1_0.name,p1_0.price,p1_0.stock_quantity from products p1_0 offset ? rows fetch first ? rows only
Hibernate: select count(p1_0.id) from products p1_0
2025-09-05T17:19:16.810+02:00  WARN 16492 --- [Leron] [nio-8080-exec-4] o.s.web.servlet.PageNotFound             : No mapping for GET /favicon.ico
2025-09-05T17:19:16.814+02:00  WARN 16492 --- [Leron] [nio-8080-exec-4] o.s.web.servlet.PageNotFound             : No endpoint GET /favicon.ico.
Hibernate: select p1_0.id,p1_0.description,p1_0.image_url,p1_0.name,p1_0.price,p1_0.stock_quantity from products p1_0 offset ? rows fetch first ? rows only
Hibernate: select count(p1_0.id) from products p1_0
2025-09-05T17:20:26.386+02:00  INFO 16492 --- [Leron] [ionShutdownHook] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
2025-09-05T17:20:26.387+02:00  INFO 16492 --- [Leron] [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2025-09-05T17:20:26.388+02:00  INFO 16492 --- [Leron] [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.

Process finished with exit code 130
```

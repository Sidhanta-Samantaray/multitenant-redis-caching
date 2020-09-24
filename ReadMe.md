# Getting Started

###### Start Redis in Local System
> docker run --name local-redis -p 6379:6379 -d redis

###### To start the Application
- Provide PostgreSQL details in application.properties file
- Run below command
     - clean spring-boot:run
- Swagger URL
    - http://localhost:8080/vibes/demo/api/swagger-ui/index.html
---
 ![image](./docs/diagram.png)
---

> **Goal of Application** 
>
> Demonstrate tenant specific cache-store in a *multi-tenant* application,
> using Spring annotations and custom **RedisCacheManager**.
>
> Idea is to prefix all applicable cache stores with the tenantId.

```json
  [
     "tenant1_customers",
     "tenant2_customers",
     "tenant3_customers",
     "tenant4_customers",
     "tenant5_customers"
   ]
```


##### Intercepting Requests
```java
@Configuration
@EnableWebMvc
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //All the APIs which requires tenant level isolation
        registry.addInterceptor(new TenantInterceptor())
                .addPathPatterns("/tenant/**"); 
        registry.addInterceptor(new AdminTenantInterceptor())
                .addPathPatterns("/admin/**");//For Super Admin
    }
}

```

###### Interceptor Usage for Setting Tenant
```java
@Slf4j
public class TenantInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(request.getRequestURI());
        ThreadLocal<String> tenantId = new ThreadLocal<>();
        tenantId.set(request.getHeader(Constants.TENANT_HTTP_HEADER));
        if (tenantId.get() == null) {
            log.error(Constants.TENANT_HTTP_HEADER + " Can not Be Blank");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Missing Header",
                    new Throwable(String.format("%s Missing", Constants.TENANT_HTTP_HEADER)));
        }
        /*
         * Perform Tenant Validation here
         *If Valid TENANT ID supplied
         *If User & Tenant Combination is valid
         */
        TenantContext.setTenant(tenantId);
        return true;
    }

}
```
###### Custom RedisCacheManager(Important Method)
```java

@Slf4j
public class CustomCacheManager extends RedisCacheManager {
    //Constructor  code removed for brevity
    
    /**
     * @param name
     * @return Prefix the cache store name with the TENANT KEY
     * For SUPER ADMIN no prefix applied
     */
    @Override
    public Cache getCache(String name) {
        log.info("Inside getCache:" + name);
        //Getting Current Tenant
        String tenantId = TenantContext.getTenant().get();
        //If its Super ADMIN do not append
        if (tenantId.equals(Constants.SUPER_ADMIN_TENANT)) {
            return super.getCache(name);
        } else if (name.startsWith(tenantId)) {
           //when tenant already added
            return super.getCache(name);
        }
        return super.getCache(tenantId + "_" + name);
    }

}
```
- API Spec
 
     ```yaml
        swagger: '2.0'
        info:
          description: Api Documentation
          version: '1.0'
          title: Api Documentation
          termsOfService: 'urn:tos'
          contact: {}
          license:
            name: Apache 2.0
            url: 'http://www.apache.org/licenses/LICENSE-2.0'
        host: 'localhost:8080'
        tags:
          - name: cache-controller
            description: Cache Controller
          - name: customer-controller
            description: Customer Controller
        paths:
          /vibes/demo/api/admin/caches:
            get:
              tags:
                - cache-controller
              summary: cacheNames
              operationId: cacheNamesUsingGET
              produces:
                - application/json
              responses:
                '200':
                  description: OK
                  schema:
                    type: array
                    items:
                      type: string
                '401':
                  description: Unauthorized
                '403':
                  description: Forbidden
                '404':
                  description: Not Found
          /vibes/demo/api/admin/caches/evict:
            delete:
              tags:
                - cache-controller
              summary: evictAll
              operationId: evictAllUsingDELETE
              produces:
                - application/json
              responses:
                '200':
                  description: OK
                '204':
                  description: No Content
                '401':
                  description: Unauthorized
                '403':
                  description: Forbidden
          /vibes/demo/api/tenant/customer:
            get:
              tags:
                - customer-controller
              summary: getCustomers
              operationId: getCustomersUsingGET
              produces:
                - application/json
              responses:
                '200':
                  description: OK
                  schema:
                    type: array
                    items:
                      $ref: '#/definitions/Customer'
                '401':
                  description: Unauthorized
                '403':
                  description: Forbidden
                '404':
                  description: Not Found
          /vibes/demo/api/tenant/customer/evict:
            delete:
              tags:
                - customer-controller
              summary: evictCaches
              operationId: evictCachesUsingDELETE
              produces:
                - application/json
              responses:
                '200':
                  description: OK
                '204':
                  description: No Content
                '401':
                  description: Unauthorized
                '403':
                  description: Forbidden
        definitions:
          Customer:
            type: object
            properties:
              id:
                type: string
              name:
                type: string
              tenant:
                type: string
            title: Customer
     
     ```


### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Data Redis (Access+Driver)](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/reference/htmlsingle/#boot-features-redis)
* [Spring Data Reactive Redis](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/reference/htmlsingle/#boot-features-redis)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Messaging with Redis](https://spring.io/guides/gs/messaging-redis/)


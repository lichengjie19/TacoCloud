
> 此项目是自学 Spring 的一个 demo，学自《Spring 实战》- 张卫滨 译。


## 第 1 章小结

- Spring 旨在**简化**开发人员所面临的挑战，比如创建 Web 应用程序、处理数据库、保护应用程序以及实现微服务。
- Spring Boot 构建在 Spring 之上，通过简化依赖管理、自动配置和运行时间洞察，使 Spring 更加易用。
- Spring 应用程序可以使用 Spring Initializer 进行初始化。Spring Initializer 是基于 Web 的应用，并且为大多数 Java 开发环境提供了原生支持。
- 在 Spring 应用上下文中，组件（通常称为 bean）既可以使用 Java 或 XML 显示声明，也可以通过组件扫描发现，还可以使用 Spring Boot 自动配置功能实现自动化配置。


## 第 2 章小结

- Spring 提供了一个强大的 Web 框架，名为 Spring MVC，能够用来为 Spring 应用开发 Web 框架。
- Spring MVC 是基于注解的，通过像 @RequestMapping、@GetMapping 和 @PostMapping 这样的注解来启用请求处理方法的声明。
- 大多数的请求处理方法最终会返回一个视图的逻辑名称，比如 Thymeleaf 模版，请求会转发到这样的视图上（同时带有任意的模型数据）。
- Spring MVC 支持校验，这是通过 Java Bean Validation API 和 Validation API 的实现（如 Hibernate Validator）完成的。
- 对于没有模型数据和逻辑处理的 HTTP GET 请求，可以使用视图控制器。
- 除了 Thymeleaf 之外，Spring 支持各种视图解决方案，包括 FreeMarker、Groovy、Templates 和 Mustache。
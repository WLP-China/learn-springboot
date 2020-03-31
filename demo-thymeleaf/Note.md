#Thymeleaf  
>Thymeleaf是一个XML/XHTML/HTML5模板引擎，可用于Web与非Web环境中的应用开发。它是一个开源的Java库，基于Apache License 2.0许可。  
>Thymeleaf提供了一个用于整合Spring MVC的可选模块，在应用开发中，你可以使用Thymeleaf来完全代替JSP、Velocity、FreeMarker或其他模板引擎。  
>Thymeleaf的主要目标在于提供一种可被浏览器正确显示的、格式良好的模板创建方式，因此也可以用作静态建模。你可以使用它创建经过验证的XML与HTML模板。相对于编写逻辑或代码，开发者只需将标签属性添加到模板中即可。接下来，这些标签属性就会在DOM（文档对象模型）上执行预先制定好的逻辑。  

默认的模板映射路径是：src/main/resources/templates

springboot1.4之后，可以使用thymeleaf3来提高效率，并且解决标签闭合问题，在pom文件中配置


#常用的表达式、标签和函数
##常用表达式
表达式|含义
:---|:---
${...}|变量表达式
*{...}|选择表达式
#{...}|消息文字表达式
@{...}|链接url 表达式
#maps|工具对象表达式

##常用标签
表达式|含义
:---|:---
th:object|替换对象。
th:text|显示文本。
th:id|标签中的ID 声明，类似HTML 标签中的归属性。
th:value|属性赋值。
th:href|定义超链接。
th:src|图片类地址引入。
th:action|定义后台控制器路径。
th:field|表单字段绑定。
th:if|条件判断语句。
th:each|盾环语句。
th:include|布局标签，替换内容到引入文件。
th:fragment|布局标签，定义一个代码片段，方便其他地方引用。

##常用函数
表达式|含义
:---|:---
#dates|日期函数。
#lists|列表函数。
#arrays|数组函数。
#strings|字符串函数。
#numbers|幸生字函捷生。
#calendars|日历函数。
#objects|对象函数。
#bools|逻辑函数。

##表达式基本对象  
在上下文变量上评估OGNL表达式时，某些对象可用于表达式以获得更高的灵活性。将从#符号开始引用这些对象（根据OGNL标准）：

表达式|含义
:---|:---
#ctx|上下文对象。
#vars|上下文变量。
#locale|上下文区域设置。
#httpServletRequest|(仅限Web Contexts）HttpServletRequest对象。
#httpSession|(仅限Web Contexts）HttpSession对象。

##Expression Utility对象
除了这些基本对象，Thymeleaf还将为我们提供一组实用程序对象，帮助我们在表达式中执行常见任务。

表达式|含义
:---|:---
#dates|java.util.Date对象的实用方法：格式化，组件提取等。
#calendars|类似于#dates，但java.util.Calendar对象。
#numbers|用于格式化数字对象的实用方法。
#strings|String对象的实用方法：contains，startsWith，prepending / appending等。
#objects|一般的对象的实用方法。
#bools|布尔评估的实用方法。
#arrays|数组的实用方法。
#lists|列表的实用方法。
#sets|集合的实用方法。
#maps|地图的实用方法。
#aggregates|用于在数组或集合上创建聚合的实用程序方法。
#messages|用于在变量表达式中获取外部化消息的实用程序方法，与使用＃{...}语法获取它们的方式相同。
#ids|用于处理可能重复的id属性的实用程序方法（例如，作为迭代的结果）

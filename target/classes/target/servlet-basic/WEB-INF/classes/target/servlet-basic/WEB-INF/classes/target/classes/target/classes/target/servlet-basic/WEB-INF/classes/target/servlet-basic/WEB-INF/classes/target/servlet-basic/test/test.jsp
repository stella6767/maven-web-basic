<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: gangmingyu
  Date: 2022/02/05
  Time: 1:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>



<h1> 현재 시각은 ${date} </h1>

<p>asdasdasdasdasjkdhakshdalsdjasljdalsdjaspjdlasjdldjasljdl</p>


<%--<form method="post" action="/test?cmd=add">--%>
<%--<button class="btn" type="submit">클릭</button>--%>
<%--</form>--%>

<%--<p id ="add">${num}</p>--%>


<button class="btn-add" >클릭</button>

<p id ="add"></p>


<%-- 주석
JSP = java server page. html 코드 안에 자바 코드를 혼재하는 게 더 편하다..

jsp page 결국 서블릿으로 변환됩니다.
jsp 페이지로부터 자바 코드를 생성하고, was가 해당하는 서블릿이 없는 경우에는  자바코드를 컴파일해가지고 서블릿 클래스를 생성해요.
서블릿에 클라이언트 요청을 전달하고, 서블릿이 요청을 처리한 결과를 응답으로 생성한다음 그걸 웹 브라우저에 전송

결국에는 저희가 개발자를 도와주는 퍼블리셔 분들이 있거든요. 그분들이 html, css 작업을 도와줘요.
페이지 안에 자바 코드가 덕지덕지 붙어있으면, 협업이 어려워져요. 그래서, 암묵적인 룰이 생기는데, 데이터 연산 같은 건 서블릿에서 하고,
표현하는 건은 이제 jsp에서 하는 거에요. mvc model 초기, view 단을 떼어놓는 거에요.

역할을 분리한 겁니다. layer 뷰 단과 servlet 단을 나눈 거에요.

이게 JSP 내장 객체를 활용한 el 리터럴 표현 법이에요.

jsp 내장 객체를 간단하게 설명하자면,
page scoope < request scope < session scope < application scope

요번에는 이제 AJAX에 대해서 알아볼게요.

동적인 데이터를 웹으로 제공하는데에 있어서 크게 두가지 당파가 존재했어요.
하나은 서버사이드에서 문서를 컴파일해가지고 보여주는 Servlet, JSP, PHP 요런 기술들이고,
다른 하나는 프로그램을 또 깔아가지고 동적으로 보여주는 activX, flash 같은 당파에요.

그리고 숨겨진 하나가 자바스크립트, 원래 자바스크립트가 이런 역할을 담당했는데, 위상이 낮았음. 옛날에는
Asynchronous JavaScript and XML, 비동기 자바스크립트와 xml 이죠.
자바스크립트를 통해서 서버에 데이터를 요청하고, xml로 응답받겠다는 거에요.
근데 요즘에는 json을 응답받아요.. 통신 방식은 xml..확신이 안됨..


전체 페이지를 리로드하지 않고 특정 부분만 자바스크립트로 조작하고 싶을 때 사용해요.
페이지 이동없이 고속으로 화면을 전환할 수 있다.
서버 처리를 기다리지 않고, 비동기 요청이 가능하다.
수신하는 데이터 양을 줄일 수 있고, 클라이언트에게 처리를 위임할 수도 있다.

JSON 개념 같은 경우도 간단해요.
"key" : "value" 로 이루어진 문자열.. 단순 문자열.

자바 오브젝트를 파이썬 프로그램에게 넘겨주면 얘가 이해를 못하겠죠. 통신언어. XML yml json 같은 것으로 통신하면
각 언어 + 프레임워크 환경마다 JSON 문자열을, 자기 환경에 맞는 오브젝트로 역직렬화해주는 라이브러리들이 존재하기 때문에
편함. 자바서버끼리 서로 통신을 할 경우, 굳이 JSON 으로 직렬화해서 던져줄 필요는 없겠죠? 근데 그래야 됩니다.
이게 왜냐면, 결국엔 그렇게 안 하면 개판으로 됨..



--%>

<script src="/js/test.js"></script>


</body>
</html>

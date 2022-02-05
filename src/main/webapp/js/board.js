
$(".btn-board").click(function(){

    //제이쿼리를 간단하게 소개하자면 그냥 자바스크립트 라이브러리.
    //문법을 좀 더 간편하게 쓸 수 있게..

    let board = {
        title : "title",
        content: "content"
    }

    console.log("click ", board)


    $.ajax({
        type: "POST",
        url: "/board?cmd=ajaxSave",
        //async: true,
        data: JSON.stringify(board),
        contentType: "application/json; charset=utf-8",
        dataType: "json"

    }).done(function(result) {

        console.log("result=> ", result);

        let board="";

        for (const element of result) {

            console.log("element", element)
            //board = element.title;
            $(".board-box").append(element.title)
            $(".board-box").append(element.content)
        }


        //AJAX 쓰는 방법이 크게 4가지 있어요.
        //Javascript xml 객체를 (원형)
        //JQeury AJax 라이브러리 모듈
        // fetch()- 자바스크립트 신규기능
        //axios(라이브러리) - 잘 되어있어요.

        //뷰를 응답받은 뒤에 AJAX 요청 -> 데이터 응답 -> 다운받은 데이터를 DOM API 에
        //접근해서 UI 와 동기화 시켜줌.

        //버츄얼 돔. 자바스크립의 데이터와 HTml Dom을 연결시킬 수 있어요.



    });

});
document.querySelector(".btn-add")
    .addEventListener("click", ()=>{
       console.log("click")

        fetch("/test?cmd=add")
            .then(function (resp){
                return resp.json();
            })
            .then(function (data){
                console.log("data", data)
                document.querySelector("#add")
                    .textContent = data
            })
    });




grant = "";
token= "";

var main = {
    init: function(){
        var _this = this;
        $("#loginBtn").on('click', function(){
            _this.login();
        }) ;

        $("#postsCall").on("click", function(){
           _this.post();
        });

    },

    login : function(){
        let username = $("#username").val();
        console.log(username);

        const data = {
            username : $("#username").val(),
            password : $("#password").val()
        };
        $.ajax({
            type: 'POST',
            url: '/auth/login',
            data: JSON.stringify(data),
            dataType : 'json',
            contentType: 'application/json'
        }).done(function(data){
            console.log(data);
            //const token = data.accessToken;

            window.grant = data.grantType + " ";
            window.token = data.accessToken;

            window.location.href = "/";
        }).fail(function(error){
            console.log(error);
        });
    },

    post : function(){
        let setheader = window.grant + window.token;

        $.ajax({
            type: 'GET',
            url: '/api/v1/posts/me',
            headers: {
                'Authorization': setheader
            },
        }).done(function(data){
            console.log(grant);
            console.log(token);
            console.log(data);
            $("#tokenId").text(data);
        }).fail(function(error){
            console.log(error);
        });

    }

};

main.init();
/*
export function prefixFactory(){
    let prefix = _prefix;
    return {
        get publicPrefix(){
            return prefix;
        },
    };
}

export function tokenFactory(){
    let token = _acToken;
    return {
        get publicToken(){
            return token;
        },
    };
}
*/

export default class Token {

    constructor(prefix, acToken){
        this.prefix = prefix;
        this.acToken = acToken;
    }

    get prefix(){
        return this._prefix;
    }
    set prefix(value){
        this._prefix = value;
    }

    get acToken(){
        return this._acToken;
    }

    set acToken(value){
        this._acToken = value;
    }

}
/*

let _prefix = 'test';
let _acToken = 'test';

export function prefixFactory(){
    let prefix = _prefix;
    return prefix;
}

export function tokenFactory(){
    let token = _acToken;
    return token;
}
*/

$("#loginBtn").click(function(){

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
        debugger;
        console.log(data);
        _prefix = data.grantType + " ";
        _acToken = data.accessToken;
        new Token(_prefix, _acToken);

        window.location.href = "/";
    }).fail(function(error){
        console.log(error);
    });
});
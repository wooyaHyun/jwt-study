
//import {prefixFactory, tokenFactory} from './token.js';

import Token from './token.js';

$(document).ready(function(){

    $(document).ajaxSend(function(event, xhr, option){
        alert("1111");
        xhr.setRequestHeader("Authorization", String(Token._prefix) + String(Token._acToken));
    });
});

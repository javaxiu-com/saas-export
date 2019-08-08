function setSidebarActive(th){
    $(th).parent("li").siblings().removeClass("active");
    $(th).parent("li").addClass("active");
}

function getCheckId() {
    var size = $("input:checkbox:checked").length;
    if(size!=1) {
        return ;
    }else {
        return $('input[type=checkbox]:checked').val();
    }
}

function formSubmit (url,sTarget){
    document.forms[0].target = sTarget
    document.forms[0].action = url;
    document.forms[0].submit();
    return true;
}
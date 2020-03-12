$(document).ready(function(){
    var submit=$("#postButton");
    var title=$("#titleText");
    var content=$("#contentText");
    submit.click(function (){
    if (!checkValidity(title.val())){
        alert("标题不符合要求，请修改");
        return;
    }
    if (!checkValidity(content.val())){
        alert("内容不符合要求，请修改");
        return;
    }
    $.post("/add",{
        _csrf:$("#csrf").val(),
        title:title.val(),
        content:content.val()
    },function (data,status) {
        if (status=="success"){
            alert("You have published a article, thank you for your contribution to the colorfulness of the world");
            $(window).attr('location','/read/'+JSON.parse(data).id);
        }else{
            alert("The system encounters some error");
        }
    })
});});
var invalidityChar=[',','.',';','?','!','\t','\n','',' '];
function checkValidity(str){
    for (let i=0;i<str.length;++i){
        if(invalidityChar.indexOf(str.charAt(i))==-1)return true;
    }
    return false;
}
function loadDataAndRendering() {
    var data = [{title: "中国人难道失掉自信力了吗", author: "鲁迅", content: "中国人难道失掉自信力了吗？愿中国青年拜托冷气，只是向上走<br>中国人难道失掉自信力了吗？愿中国青年拜托冷气，只是向上走<br>中国人难道失掉自信力了吗？愿中国青年拜托冷气，只是向上走<br>"},
        {title: "我的祖国", author: "郭兰英", content: "一条大河波浪宽，风吹稻花香两岸"}];
    var text = "";
    var len = data.length;
    for (var i = 0; i < len; ++i) {
        var item = `<div class="article">
                <div class="articleHeader">
                <p class="articleTitle">
                ${data[i].title}
        </p>
            <p class="authorName">
            ${data[i].author}
                </p>
                </div>
                <div class="articleContent">
                ${data[i].content}
                </div>
                </div>`;
        text += item;
    }
    var allList = document.getElementById("articleList");
    var aBatch = document.createElement("div");
    aBatch.setAttribute("class", "articleBatch");
    aBatch.innerHTML = text
    var childList=aBatch.childNodes;
    for (let i=0;i<childList.length;++i){
        if(childList[i].getAttribute("class")=="article"){
            var articleInner=childList[i].childNodes;
            for(let j=0;j<articleInner.length;++j){
                try{
                    if (articleInner[j].getAttribute("class")=="articleContent"){
                        articleInner[j].onclick=function(){
                            this.style.height="auto";
                            this.style.minHeight="65px";
                            //contents[i].style.height="auto";
                        };
                    }
                }catch (e) {
                    console.log(e)
                }

            }
        }
    }
    allList.appendChild(aBatch);
}
$(document).ready(function(){loadDataAndRendering();});
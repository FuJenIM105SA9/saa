/*
 * Main Javascript for Customer
 * @author Shengeih Wang
<shengeih@uitox.com>
 */

var sup_id = 'AJ1010';
var cart_id = '1';
var uitox_copyright = 'black';
var cart_img = '1';

var element_id = 'itemNumber'; // 網頁內的價格變數名稱
var element_price = 'price1'; // 網頁內料號變數名稱

var APIS_DOMAIN = 'apis.uitox.com';
var MEMBER_DOMAIN = 'member-tw1.uitox.com';
var wsseq = 'AW000002';
var SETTING = 1; // (1 左右 ; 2 上下)
var uitoxjQuery = null;
mm_includecss('http://' + APIS_DOMAIN + '/c/css/inside/uitox-cart.css');

// 此載入有優先順序
function downloadJS()
{
    if(typeof jQuery == 'undefined')
    {
        mm_includejs('http://' + APIS_DOMAIN + '/c/js/inside/jquery/jquery_1.9.1.min.js');
    }
    mm_includejs('http://' + APIS_DOMAIN + '/c/js/inside/core.js');
}

function mm_includejs(jsFile)
{
    var element = document.createElement("script");
    element.type = "text/javascript";
    element.defer = true;
    element.src  = jsFile + '?' + Math.random();
    document.body.appendChild(element);
}

function mm_includecss(cssFile)
{
    var headID = document.getElementsByTagName("head")[0];
    var element = document.createElement("link");
    element.type = "text/css";
    element.rel  = 'stylesheet';
    element.media  = 'screen';
    element.href  = cssFile + '?' + Math.random();
    headID.appendChild(element);
}

if(window.addEventListener)
{
    window.addEventListener("load", downloadJS, false);
}
else if(window.attachEvent)
{
    window.attachEvent("onload", downloadJS);
}
else
{
    window.onload = downloadJS;
}
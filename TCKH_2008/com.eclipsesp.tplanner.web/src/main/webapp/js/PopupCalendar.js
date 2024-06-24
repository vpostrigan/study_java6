var w3c=(document.getElementById)?true:false;
var ie4=(document.all && !w3c)?true:false;
var ie5=(document.all && w3c)?true:false;
var ns4=(document.layers)?true:false;
var ns6=(w3c && !document.all)?true:false;
var w_h=0; w_w=0;
dim=[31,28,31,30,31,30,31,31,30,31,30,31];
var mx=0; var my=0;
var mi=0; var yi=0;
var calA=new Array();
var cal_m, cal_y, cal, calS, calH, calMsg;
var calXO=0; var calYO=0;
var currEl='';
var now=new Date();
var calDrag=false;
var calns6=0;

function setMonth(incr){
mi+=(incr)?1:-1;
if(mi>11)mi=0;
if(mi<0)mi=11;
now.setMonth(mi);
updateCalender();
}

function setYear(incr){
yi+=(incr)?1:-1;
if(yi>50)yi=0;
if(yi<0)yi=50;
now.setFullYear(yi+1970);
updateCalender();
}

function valiDate(datestr){
if(typeof datestr=="string"){
if(datestr.indexOf("/")>0){
datestr=datestr.split('/');
var td=new Date();
td.setFullYear(parseFloat(datestr[2]));
var tyi=parseFloat(datestr[2]);
dim[1]=(((td.getFullYear()%100!=0)&&(td.getFullYear()%4==0))||(td.getFullYear()%400==0))?29:28;
if((parseInt(datestr[2])>=1970)&&(parseInt(datestr[2])<=2020)&&(parseInt(datestr[0])<=12)&&(parseInt(datestr[0])>=1)&&(parseInt(datestr[1])<=dim[parseFloat(datestr[0]-1)])&&(parseInt(datestr[1])>0)){
td.setMonth(parseFloat(datestr[0])-1);
td.setDate(parseFloat(datestr[1]));
return td;
}else{
doMssg('Date error: Will use today\'s date.');
return new Date();
}}}
doMssg('Non-date string. Starting with today\'s date.');
return new Date();
}

function doMssg(mssg){
calMsg.innerHTML=mssg;
if(mssg!=' ')setTimeout('doMssg(" ")', 2000);
}

function hideCalender(toform){
var cal=(ie4)?document.all['calender']:document.getElementById('calender');
cal.style.visibility="hidden";
calS.style.visibility="hidden";
if(toform && (currEl!=''))currEl.value=now.getDate()+'/'+(now.getMonth()+1)+'/'+now.getFullYear();
}

function updatePos(){
var w_h=(ie5||ie4)?document.body.clientHeight:window.innerHeight;
var w_w=(ie5||ie4)?document.body.clientWidth:window.innerWidth;
var elH=(ie4||ie5)?cal.clientHeight:(w3c)?cal.offsetHeight:200;
var elW=(ie4||ie5)?cal.clientWidth:(w3c)?cal.offsetWidth:300;
cal.style.left=((mx+30+elW >= w_w)? mx-30-elW:mx+20)+'px';
cal.style.top=((my+10+elH>=w_h)?my-elH:my)+'px';
cal.style.visibility="visible";
calS.style.height=elH+'px';
calS.style.width=elW+'px';
calS.style.left=parseInt(cal.style.left)+10+'px';
calS.style.top=parseInt(cal.style.top)+10+'px';
calS.style.visibility="visible";
}

function showCalender(formEl){
if(!ns4){
doMssg(' ');
now=valiDate(formEl.value);
now.ref=valiDate(formEl.value);
mi=now.getMonth();
yi=now.getFullYear()-1970;
updateCalender();
currEl=formEl;
updatePos();
}}

function updateCalender(){
dim[1]=(((now.getFullYear()%100!=0)&&(now.getFullYear()%4==0))||(now.getFullYear()%400==0))?29:28;
cal_m.innerHTML=['January','February','March','April','May','June','July','August','September','October','November','December'][mi];
cal_y.innerHTML=yi+1970;
var offsetD=new Date();
offsetD.setFullYear(yi+1970);
offsetD.setMonth(mi);
offsetD.setDate(1);
offsetD=offsetD.getDay()+1;
calDrag=false;
for(i=1;i<=42;i++){
if((i-offsetD>=0)&&(i-offsetD<dim[mi])){
calA[i].innerHTML=i-offsetD+1;
calA[i].i=i;
calA[i].o=offsetD;
if((now.ref.getDate()==i-offsetD+1)&&(now.ref.getFullYear()==now.getFullYear())&&(now.ref.getMonth()==now.getMonth())){
calA[i].className="calToday";
calA[i].onmouseover=function(){this.className="calTextH";}
calA[i].onmouseout=function(){this.className="calToday";}
}else{
calA[i].className="calText";
calA[i].onmouseover=function(){this.className="calTextH";}
calA[i].onmouseout=function(){this.className="calText";}
}
calA[i].onmousedown=function(){
now.setDate(this.i-this.o+1);
now.setFullYear(yi+1970);
now.setMonth(mi);
hideCalender(true);
}}else{
calA[i].className="calText";
calA[i].innerHTML=' ';
calA[i].onmouseover=new Function();
calA[i].onmouseout=new Function();
calA[i].onmousedown=new Function();
}}}

function calMoveNS6(){
if(calDrag)calMove();
}

function calMove(){
cal.style.left=mx-calXO+'px';
cal.style.top=my-calYO+'px';
calS.style.left=mx-calXO+10+'px';
calS.style.top=my-calYO+10+'px';
}

document.onmousemove=function(e){
if(!ns4){
mx=(ie4||ie5)?event.clientX+document.body.scrollLeft:e.pageX;
my=(ie4||ie5)?event.clientY+document.body.scrollTop:e.pageY;
if((ie4||ie5)&& calDrag)calMove();
if(calDrag)return false;
}
// ADD OTHER MOUSEMOVE EVENT COMMANDS HERE...
}

function calDragInit(e){
calXO=((ie4||ie5)?event.clientX+document.body.scrollLeft:e.pageX)-parseInt(cal.style.left);
calYO=((ie4||ie5)?event.clientY+document.body.scrollTop:e.pageY)-parseInt(cal.style.top);
calDrag=true;
return false;
}

function getEl(id){
return (ie4)?document.all[id]:document.getElementById(id);
}

window.onload=function(){
if(!ns4){
for(i=1;i<=42;i++)calA[i]=getEl('cal'+i);
cal_m=getEl("calender_m");
cal_y=getEl("calender_y");
cal=getEl("calender");
calMsg=getEl("calMsg")
calS=getEl("calenderS");
calH=getEl("calenderH");
calH.onmousedown=calDragInit;
calH.onmouseup=function(){calDrag=false;}
if(ns6)setInterval('calMoveNS6()',50);
}
// ADD OTHER WINDOW ONLOAD COMMANDS HERE...
}

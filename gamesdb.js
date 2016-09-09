/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
   console.log("NB:" + $(".gameTitle").length);
   
   $(".gameTitle").each(function(){
       findIcon($(this).text());
       //console.log($(this).text());
   });
});

function findIcon(gameTitle){
    console.log($(this).text() + " ...");
    
    $.ajax({
       url : "http://thegamesdb.net/api/GetGame.php",
       data: {"name" : gameTitle},
       dataType : "xml",
       success : function(datas){
           console.log("OK: " + datas);
       },
       error : function(){
           console.log("ERREUR: ");
       }
    });
}
$(document).ready(function(){

    $(function () {
            $(".btnPopup").click(function () {
                $(this).parent().prev().css({"background-color":"yellow","font-size":"italic","border":"3px", "z-index" :"2"});
                $(".popup").css({"display" : "block"});
                $("#overlay").css({"background-color":"grey", "z-index" :"1"})
            });  
            
        });    
});


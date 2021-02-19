(function($){
$(document).ready(function(){
  $(".addtocart11").on("click", function(e){
    e.preventDefault();
    addTocart21($(this));
});
});

function addTocart21(link){
    url = link.attr("href");
    
    $.ajax({
        type: "POST",
        url: url,
        beforeSend: function(xhr){
            xhr.setRequestHeader(crsfHeaderName, crsfValue);
        }
    }).done(function(response){
        $("#modalTitle").text("My Books");
        $("#modalBody").text(response);
        $("#Mymodal").modal();
    }).fail(function(){
        $("#modalTitle").text("My Books");
        $("#modalBody").text("Error while adding product to Mybooks");
        $("#Mymodal").modal();
    });
}
})(jQuery);
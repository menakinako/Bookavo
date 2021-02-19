$(document).ready(function(){
    $(".link-remove").on("click", function(evt){
        evt.preventDefault();
        removefromcart11($(this));
    });

});

function removefromcart11(link){
    url = link.attr("href");

    $.ajax({
        type: "POST",
        url: url,
        beforeSend: function(xhr){
            xhr.setRequestHeader(crsfHeaderName, csrfValue);
        }
    }).done(function(response){
        $("#modalTitle").text("My Books");
        if(response.includes("removed")){
            $("#Mymodal").on("hide.bs.modal", function(e){
                rowNumber = link.attr("rowNumber");
                removeBook(rowNumber);

            });
        }
        $("#modalBody").text(response);
        $("#Mymodal").modal();
        
    }).fail(function(){
        $("#modalTitle").text("My Books");
        $("#modalBody").text("Error while adding product to Mybooks");
        $("#Mymodal").modal();
    });
}

function removeBook(rowNumber){
    rowId = "row" + rowNumber;
    $("#" + rowId).remove();
}
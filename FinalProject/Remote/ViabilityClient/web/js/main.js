(function() {
    $("#data-to-hide").hide();
    $("#aviability-container-title").slideUp(0);
    $("#aviability-container-middle").slideUp(0);


    $("#aviability-container-title").stop(true, true).fadeIn({duration: 2000, queue: false}).css('display', 'none').slideDown(2000);
    $("#aviability-container-middle").stop(true, true).fadeIn({duration: 2000, queue: false}).css('display', 'none').slideDown(2000);
    
    $("#type-signal").change(function() {
        
        if ($("#type-signal").val() == "MODIFY")
            $("#data-to-hide").stop(true, true).fadeIn({duration: 2000, queue: false}).css('display', 'none').slideDown(2000);
        else if ($("#type-signal").val() == "DELETE")
            $("#data-to-hide").stop(true, true).fadeIn({duration: 2000, queue: false}).css('display', 'none').slideDown(2000);
        else
            $("#data-to-hide").slideUp(2000);
    });

})();

function loadHTML(url)
{
    var output;
    $.ajax(
            {
                url: url,
                async: false,
                success: function(data)
                {
                    output = data;
                },
                error: function()
                {
                    console.error("Errore nel caricamento del file " + url);
                }
            });
    return output;
}
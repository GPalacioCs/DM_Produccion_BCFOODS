
function fixCalendar() {
    $("span.ui-calendar").removeClass("form-control");
    $("span.ui-calendar").contents().addClass("form-control");
}

function fixInputNumber() {
    $("span.ui-inputNum").removeClass("form-control");
    $("span.ui-inputNum").contents().addClass("form-control");
}

function fixAutocomplete() {
//    $(".ui-autocomplete").removeClass("form-control");
    $(".ui-autocomplete .ui-inputfield").addClass("form-control");
//    $(".ui-autocomplete").contents().unwrap();
//    $('.ui-autocomplete-panel').remove();
}

function fixAll() {
    fixCalendar();
    fixInputNumber();
    fixAutocomplete();
}

$(document).ready(function () {
    fixAll();
    $(window).keydown(function (event) {
        if (event.keyCode == 13) {
            event.preventDefault();
            return false;
        }
    });
})

function scrollToBottom() {
    var $target = $('html,body');
    $target.animate({scrollTop: $target.height()}, 1000);
}

"use strict";

jQuery(document).ready(function($) {

    /************** Iniciar Balão de Comentários do menu *********************/
    function toolTipInit() {
        $('.menu li a').tooltip({
            placement: 'right'
        });
    }

    toolTipInit();

    $(".site-footer").animate({marginTop: 26 + '%'}, 600);

    /************** Plugin Nice Scroll *********************/
    $("html").niceScroll({
        cursorcolor: '#a71e2b',
        cursorborder: 0,
        zindex: 9999999999999999999999
    });

    /************** Navegação Responsiva *********************/

    $('.menu-toggle-btn').click(function() {
        $('.responsive_menu').stop(true, true).slideToggle();
    });

    $(".responsive_menu a").click(function() {
        $('.responsive_menu').hide();
    });

    /************** Abrir páginas dinamicamente *********************/
    
    $(".logo").click(function() {
        $(".bg-image").fadeOut('slow', function() {
            $(this).css({
                'background-image': 'url(img/fundo1.jpg)',
            }).fadeIn('slow');
        });
        $(".content").slideUp('slow');
        $(".site-footer").fadeOut('slow');
        $(".main_menu").fadeOut('slow');
        $("#slider2_container").fadeOut('slow');
        $("#slider2_container").fadeIn('slow');
        $(".main_menu").fadeIn('slow');
        $(".site-footer").fadeIn('slow');
        $(".site-footer").animate({marginTop: 26 + '%'}, 600);
    });
    
    // Ao clicar em qualquer link do menu
    $(".main_menu a").click(function() {

        var id = $(this).attr('class');
        id = id.split('-');
        $("#slider2_container").slideUp('slow');
        $("#menu-container .content").slideUp('slow');
        $("#menu-container #menu-" + id[1]).slideDown('slow');
        $(".site-footer").animate({marginTop: 2 + '%'}, 600);

        return false;
    });


    $(".main_menu .show-1").click(function() {
        $(".bg-image").fadeOut('slow', function() {
            $(this).css({
                'background-image': 'url(img/fundo7.jpg)',
            }).fadeIn('slow');
        });
        return false;
    });

    $(".main_menu .show-2").click(function() {
        $(".bg-image").fadeOut('slow', function() {
            $(this).css({
                'background-image': 'url(img/fundo2.jpg)',
            }).fadeIn('slow');
        });
        return false;
    });

    $(".main_menu .show-3").click(function() {
        $(".bg-image").fadeOut('slow', function() {
            $(this).css({
                'background-image': 'url(img/fundo3.jpg)',
            }).fadeIn('slow');
        });
        return false;
    });

    $(".main_menu .show-4").click(function() {
        $(".bg-image").fadeOut('slow', function() {
            $(this).css({
                'background-image': 'url(img/fundo4.jpg)',
            }).fadeIn('slow');
        });
        return false;
    });

    $(".main_menu .show-5").click(function() {
        $(".bg-image").fadeOut('slow', function() {
            $(this).css({
                'background-image': 'url(img/fundo5.jpg)',
            }).fadeIn('slow');
        });
        return false;
    });

    $(".main_menu .show-6").click(function() {
        $(".bg-image").fadeOut('slow', function() {
            $(this).css({
                'background-image': 'url(img/fundo6.jpg)',
            }).fadeIn('slow');
        });
        return false;
    });


    /*************************** Plugin Show the Love ************************************/
    $("span.on_img").mouseover(function() {
        $(this).addClass("over_img");
    });
    $("span.on_img").mouseout(function() {
        $(this).removeClass("over_img");
    });

    $(".love").click(function() {
        var id = $(this).attr("id");
        var dataString = 'id=' + id;
        var parent = $(this);

        $(this).fadeOut(300);
        $.ajax({
            type: "POST",
            url: "./inc/ajax_love.php",
            data: dataString,
            cache: false,
            success: function(html) {
                parent.html(html);
                parent.fadeIn(300);
            }
        });
        return false;
    });

    
});
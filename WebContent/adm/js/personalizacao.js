$(document).ready(function($) {


    $(".alterastatus").click(function() {
        mensagem = "";
        var $this = $(this);
        var status = $this.attr("status");
        var novostatus = $this.attr("novostatus");
        var numero = $this.attr("numero");
        
        if(status == "4" && novostatus == "2"){
            mensagem = $(document.getElementById("mensagem")).val();
            if(mensagem == ''){
                alert("Se recusar a solução, por favor, especifique um motivo");
                exit();
            }
        }

        $.ajax({
            type: "POST",
            url: "./class/operacaochamado.php",
            data: "id=" + numero + "&status=" + status + "&novostatus=" + novostatus + "&acao=" + 2 + "&mensagem=" + mensagem,
            success: function(html) {
                $this.parent().parent().parent().parent().fadeOut(150);
                window.location.reload();
            }
        });
    });

    $("[id=titulo]").tooltip({
        show: null,
        position: {
            my: "left top",
            at: "left bottom"
        },
        open: function(event, ui) {
            ui.tooltip.animate({top: ui.tooltip.position().top + 10}, "fast");
        }
    });

    $("#solicitacoes").click(function() {
        $("#corpo").slideUp('slow');
        $("#corpo").unload();
        $("#corpo").slideDown('slow');
        $("#corpo").load('./solicitacoes.php');
    });

    $("#duvidas").click(function() {
        $("#corpo").slideUp('slow');
        $("#corpo").unload();
        $("#corpo").slideDown('slow');
        $("#corpo").load('./duvidas.php');
    });

    $("#basedeconhecimento").click(function() {
        $("#corpo").slideUp('slow');
        $("#corpo").unload();
        $("#corpo").slideDown('slow');
        $("#corpo").load('./basedeconhecimento.php');
    });

    $("#notas").click(function() {
        $("#corpo").slideUp('slow');
        $("#corpo").unload();
        $("#corpo").slideDown('slow');
        $("#corpo").load('./notas.php');
    });

    $("#projetos").click(function() {
        $("#corpo").slideUp('slow');
        $("#corpo").unload();
        $("#corpo").slideDown('slow');
        $("#corpo").load('./projetos.php');
    });

    $("#estatisticas").click(function() {
        $("#corpo").slideUp('slow');
        $("#corpo").unload();
        $("#corpo").slideDown('slow');
        $("#corpo").load('./estatisticas.php');
    });

    $("#emprestimos").click(function() {
        $("#corpo").slideUp('slow');
        $("#corpo").unload();
        $("#corpo").slideDown('slow');
        $("#corpo").load('./emprestimos.php');
    });

    $("#contratos").click(function() {
        $("#corpo").slideUp('slow');
        $("#corpo").unload();
        $("#corpo").slideDown('slow');
        $("#corpo").load('./contratos.php');
    });

    $("#usuarios").click(function() {
        $("#corpo").slideUp('slow');
        $("#corpo").unload();
        $("#corpo").slideDown('slow');
        $("#corpo").load('./usuarios.php');
    });

    $("#grupos").click(function() {
        $("#corpo").slideUp('slow');
        $("#corpo").unload();
        $("#corpo").slideDown('slow');
        $("#corpo").load('./grupos.php');
    });

    $("#perfis").click(function() {
        $("#corpo").slideUp('slow');
        $("#corpo").unload();
        $("#corpo").slideDown('slow');
        $("#corpo").load('./perfis.php');
    });

    $("#categorias").click(function() {
        $("#corpo").slideUp('slow');
        $("#corpo").unload();
        $("#corpo").slideDown('slow');
        $("#corpo").load('./categorias.php');
    });

    $("#notificacoes").click(function() {
        $("#corpo").slideUp('slow');
        $("#corpo").unload();
        $("#corpo").slideDown('slow');
        $("#corpo").load('./notificacoes.php');
    });

    $("#sla").click(function() {
        $("#corpo").slideUp('slow');
        $("#corpo").unload();
        $("#corpo").slideDown('slow');
        $("#corpo").load('./sla.php');
    });

    $("#linksexternos").click(function() {
        $("#corpo").slideUp('slow');
        $("#corpo").unload();
        $("#corpo").slideDown('slow');
        $("#corpo").load('./linksexternos.php');
    });

    $("#outrasconfiguracoes").click(function() {
        $("#corpo").slideUp('slow');
        $("#corpo").unload();
        $("#corpo").slideDown('slow');
        $("#corpo").load('./outrasconfiguracoes.php');

    });



});
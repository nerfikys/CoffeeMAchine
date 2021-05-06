<#macro page>
<!DOCTYPE html>
<html lang="en" manifest="/static/example.appcache">
<head>
    <meta charset="UTF-8">
    <title>Health monitor</title>
    <link rel="stylesheet" href="/static/style.css">
    <link rel='manifest' href='/static/manifest.webmanifest'>
    <link rel="apple-touch-icon" href="/static/ico/ios.png">
    
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="theme-color" content="#686bd2"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.18.3/dist/bootstrap-table.min.css">
<script defer type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="https://printjs-4de6.kxcdn.com/print.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://printjs-4de6.kxcdn.com/print.min.css">

    <script>
    // Check that service workers are supported
    if ('serviceWorker' in navigator) {
      // Use the window load event to keep the page load performant
      window.addEventListener('load', () => {
        navigator.serviceWorker.register('/static/service-worker.js');
      });
    }
    </script>

</head>
<body>

<#include "navbar.ftl">
<div class="container mt-5">
<#nested>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>

</body>

</html>
</#macro>

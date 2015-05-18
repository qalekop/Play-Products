if (window.console) {
  console.log("Welcome to your Play application's JavaScript!");
}

function del(urlToDelete) {
    $.ajax({
        url: urlToDelete,
        type: 'DELETE',
        success: function(results) {
            location.reload();
        }
    });
}
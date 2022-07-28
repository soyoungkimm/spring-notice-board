<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-07-27
  Time: 오후 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $('#state_slide').click(function(){
        var checked = $('#state_slide').is(':checked');
        if(checked){
            $('#state_label').text('공개');
        }
        else {
            $('#state_label').text('비공개');
        }
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

var index = {
    init : function(){
        var _this = this;
        $('#btn-save').on('click', function() {
            _this.save();
        });
    },
    save : function(){
        var data = {
            title : $('#title').val(),
            author : $('#author').val(),
            content : $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify(data)
        }).done(() => {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(() => {
            alert(JSON.stringify(data));
        })
    }
};

index.init();
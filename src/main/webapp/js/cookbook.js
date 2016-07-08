function login_submit() {
    jQuery.post("/login/commit",
        {
            account: jQuery("#inputAccount").val(),
            password: jQuery("#inputPassword").val(),
            isRemember: jQuery("#inputRemember").prop("checked"),
        },
        function (data, status) {
            if (data.status != 200) {
                alert(data.msg);
                //location.href = "/user/login?srcUrl=" + srcUrl;
                return;
            }

            var srcUrl = jQuery("#inputSrcUrl").val();
            if (srcUrl == null || srcUrl == '') {
                srcUrl = '/';
            }
            location.href = srcUrl;
        });

}


function cookbook_create() {
    alert("title:" + jQuery("#cookBookTitle").val()
        + "\ndegree:" + jQuery("#cookBookDegree").val()
        + "\ncategories:" + jQuery("#cookBookCategories").val()
        + "\ntags:" + jQuery("#cookBookTags").val()
        + "\nmaterials:" + jQuery("#cookBookMaterials").val()
        + "\ndescription:" + jQuery("#cookBookDescription").val()
        + "\nhtmlContent:" + jQuery("#editor").html());


    jQuery.post("/cookbook/create",
        {
            title: jQuery("#cookBookTitle").val(),
            degree: jQuery("#cookBookDegree").val(),
            categories: jQuery("#cookBookCategories").val(),
            tags: jQuery("#cookBookTags").val(),
            materials: jQuery("#cookBookMaterials").val(),
            coverPic: "http://recipe0.hoto.cn/pic/recipe/l/14/d3/250644_05ec27.jpg",
            description: jQuery("#cookBookDescription").val(),
            htmlContent: jQuery("#editor").html(),
        },
        function (data, status) {
            if (data.status != 200) {
                alert(data.msg);
                //location.href = "/user/login?srcUrl=" + srcUrl;
                return;
            }
            location.href = "/cookbook/book/" + data.data;

        });

}
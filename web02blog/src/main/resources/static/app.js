const baseURL = "http://localhost:8080"
let selectedIdx = 0;
let currentStatus = "none";
$(function () {
    hideInput();
    $.get(baseURL + "/user/get/1")
        .then(value => {
            if (value.code === 105) {
                const user = value.data;
                $("#user-id").text(user.id);
                $("#user-join-date").text(user.created);
                $("#user-name").text(user.name);
            }
        });
    $.get(baseURL + "/post/list")
        .then(value => {
            if (value.code === 200) {
                const posts = value.data;
                $("#user-post-count").text(posts.length);
                $(".post-list").html("");
                posts.forEach(post => {
                    $(".post-list").append(`<li id="post-${post.id}" onclick="getPostInfo(${post.id})">${post.title}</li>`)
                })
            }
        })
})

function getPostInfo(id) {
    if (currentStatus === "none")
        $.get(baseURL + "/post/get/" + id)
            .then(value => {
                if (value.code === 201) {
                    const post = value.data;
                    $(".post-title").text(post.title);
                    $(".post-time").text(post.created);
                    $(".post-name").text(post.writer.name);
                    $(".content").text(post.content);
                    selectedIdx = id;
                } else {
                    alert("게시물이 존재하지 않습니다.");
                }
            })
            .catch(err => {
                alert("게시물이 존재하지 않습니다.");
            })
}

function showModify() {
    if (currentStatus === "create") {
        $.ajax(
            {
                url: baseURL + "/post/add",
                type: "POST",
                contentType: "application/json;",
                data: JSON.stringify({
                    userId: 1,
                    content: $("#post-content-input").val(),
                    title: $("#post-title-input").val()
                }),
                success: (data) => {
                    if (data.code === 202) {
                        const post = data.data;
                        alert("생성 성공");
                        $(".post-title").text(post.title);
                        $(".content").text(post.content);
                        $(".post-name").text(post.writer.name);
                        $(".post-time").text(post.created);
                        $(".post-list").prepend(`<li id="post-${post.id}" onclick="getPostInfo(${post.id})">${post.title}</li>`)

                    } else {
                        alert("생성에 실패하였습니다.")
                    }
                    $("#btn-modify").text("수정");
                    hideInput();
                    currentStatus = "none";
                }
            }
        )
        return;
    }
    if (currentStatus === "modify") {
        $.ajax({
            url: baseURL + "/post/modify/" + selectedIdx,
            type: "PUT",
            contentType: "application/json;",
            data: JSON.stringify({
                content: $("#post-content-input").val(),
                title: $("#post-title-input").val()
            }),
            success: (data) => {
                if (data.code === 203) {
                    const post = data.data;
                    alert("수정 성공");
                    $(".post-title").text(post.title);
                    $(".content").text(post.content);
                    $("#post-" + post.id).text(post.title);
                } else {
                    alert("수정에 실패하였습니다.")
                }
                hideInput();
                currentStatus = "none";
            }
        })

        return;
    }
    currentStatus = "modify";
    $("#post-title-input").val($(".post-title").text());
    $("#post-content-input").val($(".content").text());


    showInput();
}


function showInput() {
    $(".post-title").hide();
    $(".content").hide();
    $(".post-info").hide();

    $("#btn-delete").text("취소");
    $("#btn-next").hide();
    $("#btn-previous").hide();

    $("#post-title-input").show();
    $("#post-content-input").show();
}

function hideInput() {

    $(".post-title").show();
    $(".content").show();
    $(".post-info").show();

    $("#btn-delete").text("삭제");
    $("#btn-next").show();
    $("#btn-previous").show();
    $("#post-title-input").hide();
    $("#post-content-input").hide();
}

function createPost() {
    currentStatus = "create";
    $("#post-title-input").val("");
    $("#post-content-input").val("");
    $("#btn-modify").text("생성");
    showInput();
}

function deletePost() {
    if (currentStatus === "modify" || currentStatus === "create") {
        hideInput();
        currentStatus = "none";
        return;
    }
    $.ajax({
        type: "DELETE",
        url: baseURL + "/post/remove/" + selectedIdx,
        success: (data) => {
            if (data.data === true) {
                alert("삭제가 완료되었습니다.");
            }
        }
    })
}

function getTopPost() {
    $.get(baseURL + "/post/user/1")
        .then(value => {
            if (value.code === 201) {
                const post = value.data;
                $(".post-title").text(post.title);
                $(".post-time").text(post.created);
                $(".post-name").text(post.writer.name);
                $(".content").text(post.content);
            }
        })
}

getTopPost();
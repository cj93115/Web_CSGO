 $(function(){
var username= $("#aUsername").text();
     $(function(){
         console.log("测试");
         console.log("name:"+$("#aUsername").val());
         if(username==null || username==""){
             $("#aUsername").hide();
             $("#login_click").show();
             $("#reg_click").show();
         }else {
             $("#aUsername").show();
             $("#login_click").hide();
             $("#reg_click").hide();
         }

     });

     $(".owl-carousel.qie1").owlCarousel({
        loop:true,
        items: 7,
        autoplay: true,
        autoplaySpeed:600,
        autoplayTimeout:2000,
        autoplayHoverPause:true,
      
        dots:false,

    });

    $("#reg_click").click(function(event) {
         $(".reg_mask").fadeIn();
         $(".reg_box.reg").fadeIn();
    });

    $("#reg_click1").click(function(event) {
          $(".reg_box").fadeOut();
         $(".reg_box.reg").fadeIn();
    });




    $("#login_click").click(function(event) {
         $(".reg_mask").fadeIn();
         $(".reg_box.login").fadeIn();
    });



    $(".reg_close").click(function(event) {
         $(".reg_mask").fadeOut();
         $(".reg_box").fadeOut();
    });

    function login(){
         $(".reg_box").fadeOut();
         $(".reg_box.login").fadeIn();
    }


// ================= 注册 ================

    $("#reg_button").click(function(event) {
      var username= $("#username").val();
      var password= $("#password").val();
      var tel= $("#tel").val();
      var email= $("#email").val();
      var qq= $("#qq").val();
      
      if(username=='' || password=='' || tel==''){
         layer.msg('帐号密码手机号不能为空');
      }else{
           
            
 
                 var domain_url = "http://localhost:8986/UserRegisterController/register";
                 var argus={"State":1,"AccountType":"MemberUser","UserName":username,"Password":password,"Mobile":tel,"Email":email,"QQ":qq};
                 $.ajax({
                   // url:'my.json', 
                    url:domain_url,
                    type: 'post', 
                    dataType: 'json',
                    data:JSON.stringify(argus),
                    contentType:"application/json",
                    success: function(datas){
                        if(datas.httpCode == 200){
                           
                           
                           layer.msg('注册成功，请登录!', function(){
                                 login();
                           }); 


                        }else{
                            layer.msg('注册失败！');
                        }
                    },

                    error:function (message) {
                        layer.msg('注册失败！');
                    }
                })

            
      }
        
    });


// ================= 登录 ================

    $("#login_button").click(function(event) {
      var username= $("#login_username").val();
      var password= $("#login_password").val();

      
      if(username=='' || password==''){
         layer.msg('帐号密码不能为空');
      }else{
                 var domain_url = "http://localhost:8986/LoginController/login";
             console.log("连接："+domain_url);
                 var argus={"username":username,"password":password,"userType":0};
                 $.ajax({ 

                   // url:'my.json', 
                    url:domain_url,
                    type: 'post', 
                    dataType: 'json',
                    data:JSON.stringify(argus),
                    contentType:"application/json",
                    success: function(datas){
                        console.log(datas);

                        if(datas.authenticated){
                            layer.msg('登录成功');
                            console.log("已修改");
                            window.location.reload();
                        }else{
                            layer.msg('登录失败！');
                        }
                    },

                    error:function (message) {
                        layer.msg('登录失败！');
                    }
                })

            
      }
        
    });




});
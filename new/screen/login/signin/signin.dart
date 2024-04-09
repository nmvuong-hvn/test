import 'package:datn/constant/asset_image.dart';
import 'package:datn/constant/color.dart';
import 'package:datn/constant/routes.dart';
import 'package:datn/firebase_helper/firebase_authentication/firebase_auth_helper.dart';
import 'package:datn/screen/home/home_view.dart';
import 'package:datn/screen/login/signin/sign_in_controller.dart';
import 'package:datn/screen/login/signup/signup.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:lottie/lottie.dart';
import 'package:shared_preferences/shared_preferences.dart';
import '../../../constant/constants.dart';
import '../../../widgets/button/primary_button.dart';

class Signin extends StatefulWidget {
  @override
  State<Signin> createState() => _SigninState();
}

class _SigninState extends State<Signin> {
  final emailController = TextEditingController();
  final passWordController = TextEditingController();
  final controller  = Get.put(SignInController());
  bool isShowPassword = true;
  bool remember = false;



  // Retrieve login data
  void getLoginData() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? username = prefs.getString('username');
    String? password = prefs.getString('password');
    bool? checked = prefs.getBool('check');
    if (checked!) {
      emailController.text = username!;
      passWordController.text = password!;
      setState(() {
        remember = checked;
      });
    }
  }

  @override
  void initState() {
    super.initState();
    getLoginData();
  }

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      onWillPop: () {
        return Future.value(true);
      },
      child: Scaffold(
        resizeToAvoidBottomInset: false,
        body: SafeArea(
          child: Padding(
            padding: const EdgeInsets.all(20),
            child: SingleChildScrollView(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Center(
                    child: Text(
                      "Đăng Nhập",
                      style: TextStyle(
                          fontSize: 45,
                          fontWeight: FontWeight.bold,
                          fontFamily: 'Handle',
                          color: ColorInstance.backgroundColor),
                    ),
                  ),
                  const SizedBox(height: 5),
                  Center(
                    child: Text(
                      "Quản lý nhà trọ dễ dàng",
                      style: TextStyle(
                          fontSize: 25,
                          fontFamily: 'Handle',
                          color: ColorInstance.backgroundColor),
                    ),
                  ),
                  Center(
                      child: SizedBox(
                          height: 250,
                          child: Lottie.asset('assets/json/login.json'))),
                  const SizedBox(height: 20),
                  TextFormField(
                    keyboardType: TextInputType.emailAddress,
                    controller: emailController,
                    decoration: InputDecoration(
                        labelText: "E-mail",
                        labelStyle:
                            TextStyle(color: ColorInstance.backgroundColor)),
                  ),
                  const SizedBox(height: 20),
                  TextFormField(
                    controller: passWordController,
                    obscureText: isShowPassword,
                    decoration: InputDecoration(
                        labelText: "Mật khẩu",
                        labelStyle: TextStyle(color: ColorInstance.backgroundColor),
                        suffixIcon: CupertinoButton(
                          child: Image.asset(
                            isShowPassword
                                ? AssetsImage.instance.imgVisible
                                : AssetsImage.instance.imgInvisible,
                            height: 20,
                            width: 20,
                          ),
                          onPressed: () {
                            setState(() {
                              isShowPassword = !isShowPassword;
                            });
                          },
                        )),
                  ),
                  const SizedBox(height: 10),
                  Row(
                    children: [
                      Checkbox(
                          value: remember,
                          side: BorderSide(color: ColorInstance.backgroundColor),
                          activeColor: ColorInstance.backgroundColor,
                          onChanged: (value) {
                            setState(() {
                              remember = value!;
                            });
                          }),
                      const Text("Nhớ mật khẩu",
                          style: TextStyle(
                              color: Colors.teal,
                              fontSize: 17,
                              fontWeight: FontWeight.bold)),
                    ],
                  ),
                  const SizedBox(height: 10),
                  PrimaryButton(
                      onPressed: () async {
                        controller.login(emailController.text, passWordController.text,remember,context);
                      },
                      title: "Đăng nhập"),
                  const SizedBox(height: 20),

                  const Center(
                    child: Text(
                      "Chưa có tài khoản ?",
                      style: TextStyle(
                        fontSize: 18,
                      ),
                    ),
                  ),
                  Center(
                      child: CupertinoButton(
                    onPressed: () {
                      Routes.instance.push(widget: Signup(), context: context);
                    },
                    child: Text(
                      "Đăng ký",
                      style: TextStyle(
                          color: ColorInstance.backgroundColor,
                          decoration: TextDecoration.underline,
                          fontWeight: FontWeight.bold),
                    ),
                  )),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}

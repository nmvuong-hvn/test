import 'package:datn/constant/asset_image.dart';
import 'package:datn/constant/color.dart';
import 'package:datn/constant/constants.dart';
import 'package:datn/constant/routes.dart';
import 'package:datn/firebase_helper/firebase_authentication/firebase_auth_helper.dart';
import 'package:datn/screen/login/signin/signin.dart';
import 'package:datn/widgets/button/primary_button.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class Signup extends StatefulWidget {
  @override
  State<Signup> createState() => _SignupState();
}

class _SignupState extends State<Signup> {
  final emailController = TextEditingController();
  final passwordController = TextEditingController();
  bool isShowPassword = true;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: false,
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.all(15),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const SizedBox(height: 20),
              Center(
                child: Text(
                  "Tạo Tài Khoản",
                  style: TextStyle(
                      fontSize: 45,
                      fontWeight: FontWeight.bold,
                      fontFamily: 'Handle',
                      color: ColorInstance.backgroundColor),
                ),
              ),
              const SizedBox(height: 15),
              Text(
                "Đăng Ký",
                style: TextStyle(
                    fontSize: 30,
                    fontWeight: FontWeight.normal,
                    fontFamily: 'Handle',
                    color: ColorInstance.backgroundColor),
              ),
              const SizedBox(height: 20),
              TextFormField(
                controller: emailController,
                keyboardType: TextInputType.emailAddress,
                decoration: InputDecoration(
                    labelText: "E-mail",
                    labelStyle:
                        TextStyle(color: ColorInstance.backgroundColor)),
              ),
              const SizedBox(height: 20),
              TextFormField(
                  controller: passwordController,
                  obscureText: isShowPassword,
                  decoration: InputDecoration(
                      labelText: "Mật khẩu",
                      labelStyle:
                          TextStyle(color: ColorInstance.backgroundColor),
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
                      ))),
              const SizedBox(height: 30),
              PrimaryButton(
                  onPressed: () async {
                    bool isValidated = signupValidation(
                        context, emailController.text, passwordController.text);
                    if (isValidated) {
                      bool isLogined = await FirebaseAuthHelper.instance.signup(
                          context,
                          emailController.text,
                          passwordController.text);
                      if (isLogined) {
                        showMessage("Đăng ký thành công !");
                        Routes.instance.pushAndRemoveUntil(
                            widget: Signin(), context: context);
                      }
                    }
                  },
                  title: "Đăng ký"),
              const SizedBox(height: 30),
              const Center(
                child: Text(
                  "Đã có tài khoản ?",
                  style: TextStyle(
                    fontSize: 18,
                  ),
                ),
              ),
              Center(
                  child: CupertinoButton(
                onPressed: () {
                  Navigator.pop(context);
                },
                child: Text(
                  "Đăng nhập",
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
    );
  }
}

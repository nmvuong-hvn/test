
import 'package:datn/constant/constants.dart';
import 'package:datn/screen/home/home_view.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/cupertino.dart';
import 'package:get/get.dart';
import 'package:shared_preferences/shared_preferences.dart';

class SignInController extends GetxController {

  var firebaseAuth = FirebaseAuth.instance;
  var stateLogin = StateLogin.LOADING.obs;
  var errorValidation = "".obs;

   bool loginValidation(String email, String password) {
    if (email.isEmpty && password.isEmpty) {
      Get.snackbar("Lỗi", "Dữ liệu không được để trống !",snackPosition: SnackPosition.BOTTOM) ;
      return false;
    } else if (email.isEmpty) {
      Get.snackbar("Lỗi", "E-mail không được để trống !",snackPosition: SnackPosition.BOTTOM) ;
      return false;
    } else if (password.isEmpty) {
      Get.snackbar("Lỗi", "Mật khẩu không được để trống !",snackPosition: SnackPosition.BOTTOM) ;
      return false;
    } else {
      return true;
    }
  }

  void login(String email , String passWord, bool remember, BuildContext context) async {
      bool isValidated = loginValidation(email, passWord);
      if(isValidated) {
        try {
          showLoaderDialog(context);
          await firebaseAuth.signInWithEmailAndPassword(
              email: email, password: passWord);
          Navigator.of(context).pop(); //an dialog
          Get.snackbar("Thành công", "Đăng nhập thành công!",snackPosition: SnackPosition.BOTTOM) ;
          saveLoginData(email,passWord, remember);
          Get.off(HomeView());
        }on Exception catch (_, exception){
          Get.snackbar("Lỗi", exception.toString(),snackPosition: SnackPosition.BOTTOM) ;
          Navigator.of(context).pop(); //an dialog
        }
      }
  }

  // Save login data
  void saveLoginData(String username, String password, bool remember) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    if (remember) {
      prefs.setString('username', username);
      prefs.setString('password', password);
      prefs.setBool('check', remember);
    } else {
      prefs.remove('username');
      prefs.remove('password');
      prefs.remove('check');
    }
  }
}

enum StateLogin {
  LOADING ,
  SUCCESS ,
  ERROR
}


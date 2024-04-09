import 'package:datn/AppRoutes.dart';
import 'package:datn/constant/theme.dart';
import 'package:datn/firebase_helper/firebase_authentication/firebase_auth_helper.dart';
import 'package:datn/screen/login/signin/signin.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      title: 'House Management',
      debugShowCheckedModeBanner: false,
      theme: themeData,
      initialRoute: AppRoutes.loginRoute,
      getPages: AppRoutes.routes,
    );
  }
}

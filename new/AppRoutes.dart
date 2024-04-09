import 'package:datn/feature/create_bill/create_bill.dart';
import 'package:datn/feature/create_electric_water/create_electric_water.dart';
import 'package:datn/feature/list_detail_order/list_detail_order_screen.dart';
import 'package:datn/feature/setting_house/setting_house.dart';
import 'package:datn/feature/watch_order/watch_order_screen.dart';
import 'package:datn/screen/add_person/person_view.dart';
import 'package:datn/screen/home/home_view.dart';
import 'package:datn/screen/home_details/home_details.dart';
import 'package:datn/screen/login/signin/signin.dart';
import 'package:datn/screen/login/signup/signup.dart';
import 'package:get/get_navigation/src/routes/get_route.dart';

class AppRoutes {
  static const loginRoute = "/";
  static const registerRoute = "/Register";
  static const homeRoute = "/HomeView";
  static const homeDetailRoute = "/HomeDetailRoute";
  static const settingRoute = "/SettingRoute";
  static const writeElectricAndWaterRoute = "/WriteElectricAndWaterRoute";
  static const addPersonRoute = "/AddPersonRoute";
  static const createBillRoute = "/CreateBillRoute";
  static const watchOrderRoute = "/WatchOrderRoute";
  static const listOrderRoute = "/ListOrderRoute";

  static final List<GetPage> routes = [
    GetPage(name: loginRoute, page: () => Signin()),
    GetPage(name: registerRoute, page: () => Signup()),
    GetPage(name: homeRoute, page: () => const HomeView()),
    GetPage(name: homeDetailRoute, page: () => const HomeDetailView()),
    GetPage(name: settingRoute, page: () => const SettingHouseScreen()),
    GetPage(name: writeElectricAndWaterRoute, page: () => const CreateElectricAndWaterScreen()),
    GetPage(name: addPersonRoute, page: () =>  const PersonView()),
    GetPage(name: createBillRoute, page: () =>  const CreateBillingScreen()),
    GetPage(name: watchOrderRoute, page: () =>  const WatchOrderScreen()),
    GetPage(name: listOrderRoute, page: () =>  const ListDetailOrderScreen()),
  ];
}
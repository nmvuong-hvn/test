import 'package:datn/screen/home_details/home_details_controller.dart';
import 'package:get/get.dart';

class HomeDetailBinding extends Bindings {
  @override
  void dependencies() {
    Get.lazyPut(() => HomeDetailsController());
  }

}
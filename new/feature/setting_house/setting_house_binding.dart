import 'package:datn/feature/setting_house/setting_house_controller.dart';
import 'package:get/get.dart';

class SettingHouseBinding extends Bindings {
  @override
  void dependencies() {
    Get.put(() => SettingHouseController());
  }
}

import 'package:datn/feature/create_electric_water/create_electric_water_controller.dart';
import 'package:get/get.dart';

class CreateElectricAndWaterBinding extends Bindings {
  @override
  void dependencies() {

    Get.lazyPut(() => CreateElectricAndWaterController());
  }

}
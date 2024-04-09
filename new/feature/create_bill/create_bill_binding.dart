
import 'package:datn/feature/create_bill/create_bill_controller.dart';
import 'package:get/get.dart';

class CreateBillBinding extends Bindings {
  @override
  void dependencies() {
    Get.lazyPut(() => CreateBillController());
  }

}
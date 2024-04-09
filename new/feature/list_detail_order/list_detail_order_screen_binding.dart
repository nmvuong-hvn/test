
import 'package:datn/feature/list_detail_order/list_detail_order_controller.dart';
import 'package:get/get.dart';

class ListDetailOrderScreenBinding extends Bindings {
  @override
  void dependencies() {
    Get.put(()=> ListDetailOrderController());
  }

}
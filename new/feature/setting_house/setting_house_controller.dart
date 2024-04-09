import 'package:datn/feature/setting_house/setting_house_model.dart';
import 'package:get/get.dart';
import 'package:get_storage/get_storage.dart';

class SettingHouseController extends GetxController{
  var storage = GetStorage();

  void saveData(SettingHouseModel settingHouseModel) {
    storage.write("dayOfMonth", settingHouseModel.dayOfMonth);
    storage.write("priceElectricity", settingHouseModel.priceElectricity);
    storage.write("priceWater", settingHouseModel.priceWater);
    storage.write("priceRoom", settingHouseModel.priceRoom);
    storage.write("priceInternet", settingHouseModel.priceInternet);
    storage.write("priceSecurity", settingHouseModel.priceSecurity);
  }

  SettingHouseModel getData() {
    final dayOfMonth = storage.read("dayOfMonth") ?? 0;
    final priceElectricity = storage.read("priceElectricity") ?? 0;
    final priceWater = storage.read("priceWater") ?? 0;
    final priceRoom = storage.read("priceRoom") ?? 0;
    final priceInternet = storage.read("priceInternet") ?? 0;
    final priceSecurity = storage.read("priceSecurity") ?? 0;
    return SettingHouseModel(dayOfMonth, priceElectricity, priceWater, priceRoom, priceInternet, priceSecurity);
  }
}
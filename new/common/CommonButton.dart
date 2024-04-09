import 'package:flutter/material.dart';

class CommonButton extends StatelessWidget {
  String nameButton;
  VoidCallback onClick;

  CommonButton({super.key, required this.nameButton, required this.onClick});

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: onClick,
      child: Container(
        decoration: BoxDecoration(
          color: Colors.teal,
          borderRadius: BorderRadius.circular(12),
        ),
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 32, vertical: 12),
          child: Text(nameButton, style: const TextStyle(color: Colors.white),),
        ),
      ),
    );
  }
}

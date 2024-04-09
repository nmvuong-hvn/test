import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class CommonPickerImageElecAndWater extends StatefulWidget {
  String title;
  String imageUrl;
  VoidCallback selected;

  CommonPickerImageElecAndWater({super.key, required this.title,
    required this.imageUrl, required this.selected});

  @override
  State<CommonPickerImageElecAndWater> createState() =>
      _CommonPickerImageElecAndWater();
}

class _CommonPickerImageElecAndWater
    extends State<CommonPickerImageElecAndWater> {
  @override
  void initState() {
    super.initState();
  }
  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.start,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            SizedBox(width: 120, child: Text(widget.title, style: const TextStyle(fontWeight: FontWeight.bold))),
            InkWell(
                onTap: widget.selected ,
                child: const Expanded(child: Icon(Icons.image_aspect_ratio_outlined)))
          ],
        ),
        SizedBox(
          height: 100,
          width: 100,
          child: Image.network(widget.imageUrl.isNotEmpty ? widget.imageUrl : "https://pixsector.com/cache/517d8be6/av5c8336583e291842624.png"),
        )
      ],
    );
  }
}

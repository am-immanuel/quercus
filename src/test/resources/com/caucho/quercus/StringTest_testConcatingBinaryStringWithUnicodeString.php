<?php

$s_length_map = array(
    "\x00" => 1, "\x01" => 1, "\x02" => 1, "\x03" => 1, "\x04" => 1, "\x05" => 1, "\x06" => 1, "\x07" => 1,
    "\x08" => 1, "\x09" => 1, "\x0a" => 1, "\x0b" => 1, "\x0c" => 1, "\x0d" => 1, "\x0e" => 1, "\x0f" => 1,
    "\x10" => 1, "\x11" => 1, "\x12" => 1, "\x13" => 1, "\x14" => 1, "\x15" => 1, "\x16" => 1, "\x17" => 1,
    "\x18" => 1, "\x19" => 1, "\x1a" => 1, "\x1b" => 1, "\x1c" => 1, "\x1d" => 1, "\x1e" => 1, "\x1f" => 1,
    "\x20" => 1, "\x21" => 1, "\x22" => 1, "\x23" => 1, "\x24" => 1, "\x25" => 1, "\x26" => 1, "\x27" => 1,
    "\x28" => 1, "\x29" => 1, "\x2a" => 1, "\x2b" => 1, "\x2c" => 1, "\x2d" => 1, "\x2e" => 1, "\x2f" => 1,
    "\x30" => 1, "\x31" => 1, "\x32" => 1, "\x33" => 1, "\x34" => 1, "\x35" => 1, "\x36" => 1, "\x37" => 1,
    "\x38" => 1, "\x39" => 1, "\x3a" => 1, "\x3b" => 1, "\x3c" => 1, "\x3d" => 1, "\x3e" => 1, "\x3f" => 1,
    "\x40" => 1, "\x41" => 1, "\x42" => 1, "\x43" => 1, "\x44" => 1, "\x45" => 1, "\x46" => 1, "\x47" => 1,
    "\x48" => 1, "\x49" => 1, "\x4a" => 1, "\x4b" => 1, "\x4c" => 1, "\x4d" => 1, "\x4e" => 1, "\x4f" => 1,
    "\x50" => 1, "\x51" => 1, "\x52" => 1, "\x53" => 1, "\x54" => 1, "\x55" => 1, "\x56" => 1, "\x57" => 1,
    "\x58" => 1, "\x59" => 1, "\x5a" => 1, "\x5b" => 1, "\x5c" => 1, "\x5d" => 1, "\x5e" => 1, "\x5f" => 1,
    "\x60" => 1, "\x61" => 1, "\x62" => 1, "\x63" => 1, "\x64" => 1, "\x65" => 1, "\x66" => 1, "\x67" => 1,
    "\x68" => 1, "\x69" => 1, "\x6a" => 1, "\x6b" => 1, "\x6c" => 1, "\x6d" => 1, "\x6e" => 1, "\x6f" => 1,
    "\x70" => 1, "\x71" => 1, "\x72" => 1, "\x73" => 1, "\x74" => 1, "\x75" => 1, "\x76" => 1, "\x77" => 1,
    "\x78" => 1, "\x79" => 1, "\x7a" => 1, "\x7b" => 1, "\x7c" => 1, "\x7d" => 1, "\x7e" => 1, "\x7f" => 1,
    "\x80" => 0, "\x81" => 0, "\x82" => 0, "\x83" => 0, "\x84" => 0, "\x85" => 0, "\x86" => 0, "\x87" => 0,
    "\x88" => 0, "\x89" => 0, "\x8a" => 0, "\x8b" => 0, "\x8c" => 0, "\x8d" => 0, "\x8e" => 0, "\x8f" => 0,
    "\x90" => 0, "\x91" => 0, "\x92" => 0, "\x93" => 0, "\x94" => 0, "\x95" => 0, "\x96" => 0, "\x97" => 0,
    "\x98" => 0, "\x99" => 0, "\x9a" => 0, "\x9b" => 0, "\x9c" => 0, "\x9d" => 0, "\x9e" => 0, "\x9f" => 0,
    "\xa0" => 0, "\xa1" => 0, "\xa2" => 0, "\xa3" => 0, "\xa4" => 0, "\xa5" => 0, "\xa6" => 0, "\xa7" => 0,
    "\xa8" => 0, "\xa9" => 0, "\xaa" => 0, "\xab" => 0, "\xac" => 0, "\xad" => 0, "\xae" => 0, "\xaf" => 0,
    "\xb0" => 0, "\xb1" => 0, "\xb2" => 0, "\xb3" => 0, "\xb4" => 0, "\xb5" => 0, "\xb6" => 0, "\xb7" => 0,
    "\xb8" => 0, "\xb9" => 0, "\xba" => 0, "\xbb" => 0, "\xbc" => 0, "\xbd" => 0, "\xbe" => 0, "\xbf" => 0,
    "\xc0" => 2, "\xc1" => 2, "\xc2" => 2, "\xc3" => 2, "\xc4" => 2, "\xc5" => 2, "\xc6" => 2, "\xc7" => 2,
    "\xc8" => 2, "\xc9" => 2, "\xca" => 2, "\xcb" => 2, "\xcc" => 2, "\xcd" => 2, "\xce" => 2, "\xcf" => 2,
    "\xd0" => 2, "\xd1" => 2, "\xd2" => 2, "\xd3" => 2, "\xd4" => 2, "\xd5" => 2, "\xd6" => 2, "\xd7" => 2,
    "\xd8" => 2, "\xd9" => 2, "\xda" => 2, "\xdb" => 2, "\xdc" => 2, "\xdd" => 2, "\xde" => 2, "\xdf" => 2,
    "\xe0" => 3, "\xe1" => 3, "\xe2" => 3, "\xe3" => 3, "\xe4" => 3, "\xe5" => 3, "\xe6" => 3, "\xe7" => 3,
    "\xe8" => 3, "\xe9" => 3, "\xea" => 3, "\xeb" => 3, "\xec" => 3, "\xed" => 3, "\xee" => 3, "\xef" => 3,
    "\xf0" => 4, "\xf1" => 4, "\xf2" => 4, "\xf3" => 4, "\xf4" => 4, "\xf5" => 4, "\xf6" => 4, "\xf7" => 4,
    "\xf8" => 5, "\xf9" => 5, "\xfa" => 5, "\xfb" => 5, "\xfc" => 6, "\xfd" => 6, "\xfe" => 0, "\xff" => 0,
);

$result = array();

$tmpfname1 = tempnam("", "UTF8Reader-problems");

$pdf = file_get_contents("src/test/resources/com/caucho/quercus/test.pdf");
file_put_contents($tmpfname1, "" . $pdf . "");
$read1 = file_get_contents($tmpfname1);
$result['pdfStringConcatResult'] = $pdf == $read1;

$pdf = file_get_contents("src/test/resources/com/caucho/quercus/emptyFile.pdf");
file_put_contents($tmpfname1, "äüö" . $pdf . "äüö");
$read1 = file_get_contents($tmpfname1);
$result['emptyStringConcatResult'] = $read1 === "äüöäüö";
$result['emptyStringConcatLength'] = strlen($read1);

unlink($tmpfname1);

$string = "äöüäöü";
$result['unicodeBinaryComparison1'] = $read1[0] === $string[0];
$result['unicodeBinaryComparison2'] = $read1[1] === $string[1];
$result['lengthMapBinary'] = array_key_exists($read1[0], $s_length_map);
$result['lengthMapUnicode'] = array_key_exists($string[0], $s_length_map);

return $result;
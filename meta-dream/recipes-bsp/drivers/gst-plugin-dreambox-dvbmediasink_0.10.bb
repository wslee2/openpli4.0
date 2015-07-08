SUMMARY = "Plugin for gstreamer: dvbmediasink"
SECTION = "multimedia"
LICENSE = "MIT | LGPLv2.1"
LIC_FILES_CHKSUM = "file://src/gstdvbaudiosink.c;beginline=1;endline=45;md5=023ebb8eaef9b8cce8591a9d96638392 \
                    file://src/gstdvbvideosink.c;beginline=1;endline=44;md5=b597d3f0a4e3b49db42d2b5140bd7004"
DEPENDS = "gstreamer gst-plugins-base"
PR = "r10"

SCHWERKRAFT_PROJECT = "dvbmediasink"

S = "${WORKDIR}/git"

do_configure_prepend() {
	sed -i 's/AC_INIT.*$/AC_INIT(gst-plugin-dreambox-dvbmediasink, 0.10, @pli4)/' ${S}/configure.ac
	sed -i 's/AM_INIT_AUTOMAKE.*$/AM_INIT_AUTOMAKE([foreign subdir-objects])/' ${S}/configure.ac
}

inherit autotools schwerkraft-git pkgconfig

SRCREV = "7671ff6f07a70151ef183e0f501097de5cf7f1b3"

SRC_URI += " \
	file://getdecodertime.patch \
	file://0001-Handle-DTS-Passthrough-and-Downmix.patch \
	file://0002-Set-only-by-hardware-supported-audio-mpeg-4-profile.patch \
"

FILES_${PN} = "${libdir}/gstreamer-0.10/*.so"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/*.la"
FILES_${PN}-staticdev += "${libdir}/gstreamer-0.10/*.a"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug"

DESCRIPTION = "gstreamer dtsdownmix plugin"
SECTION = "multimedia"
PRIORITY = "optional"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=7fbc338309ac38fefcd64b04bb903e34"

DEPENDS = "gstreamer gst-plugins-base libdca"

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/athoik/${PN};branch=master"

S = "${WORKDIR}/git"

do_configure_prepend() {
	sed -i 's/AC_INIT.*$/AC_INIT(gst-plugin-dreambox-dvbmediasink, 0.10, @pli4)/' ${S}/configure.ac
	sed -i 's/AM_INIT_AUTOMAKE.*$/AM_INIT_AUTOMAKE([foreign subdir-objects])/' ${S}/configure.ac
}

inherit gitpkgv pkgconfig

PV = "0.10.0+git${SRCPV}"
PKGV = "0.10.0+git${GITPKGV}"
PR = "r0"

inherit autotools pkgconfig

FILES_${PN} = "${libdir}/gstreamer-0.10/*.so*"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/*.la"
FILES_${PN}-staticdev += "${libdir}/gstreamer-0.10/*.a"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# Creates the "feed", packages not required for the image
# but that should be built for the feed so that other
# components may use them and install on demand.

# Trick: We want to create the package index, and we don't actually
# package anything, so we "inherit" the package indexer recipe.
require recipes-core/meta/package-index.bb

# We have a GPLv2 license for this recipe...
require conf/license/openpli-gplv2.inc

# Depend on the image, so that it gets build
DEPENDS = "openpli-enigma2-image"

OPTIONAL_PACKAGES_BROKEN = "samba"
OPTIONAL_PACKAGES ?= ""
OPTIONAL_BSP_PACKAGES ?= ""
OPTIONAL_PACKAGES += " \
	autofs \
	autossh \
	ctorrent \
	cups \
	djmount \
	dvbsnoop \
	dvdfs \
	evtest \
	gdb \
	hddtemp \
	hdparm \
	inadyn-mt \
	iperf \
	joe \
	mc \
	mpd \
	mtd-utils \
	nano \
	ntfs-3g \
	ntp \
	ofgwrite \
	openresolv \
	openssh \
	openvpn \
	parted \
	procps \
	pyload \
	python-requests \
	python-mechanize \
	rsync \
	rtorrent \
	sabnzbd \
	smartmontools \
	smbnetfs \
	strace \
	tcpdump \
	tmux \
	transmission \
	vim \
	xfsprogs \
	zeroconf \
	zram \
	${OPTIONAL_BSP_PACKAGES} \
	"

ENIGMA2_OPTIONAL = " \
	enigma2-pliplugins \
	enigma2-plugin-drivers-usbserial \
	enigma2-plugin-extensions-ambx \
	enigma2-plugin-extensions-dlnabrowser \
	enigma2-plugin-extensions-dlnaserver \
	enigma2-plugin-extensions-et-livestream \
	enigma2-plugin-extensions-openuitzendinggemist \
	enigma2-plugin-extensions-tuxcom \
	enigma2-plugin-extensions-xmltvimport \
	enigma2-plugin-security-firewall \
	enigma2-plugin-skins-pli-hd \
	enigma2-plugin-skins-pli-hd-fullnight \
	enigma2-skins \
	picons-enigma2-meta \
	softcams-enigma2-meta \
	packagegroup-openplugins \
	${@base_contains("MACHINE_FEATURES", "blindscan-dvbs", "enigma2-plugin-systemplugins-satscan" , "", d)} \
	${@base_contains("MACHINE_FEATURES", "dreambox", "", "enigma2-plugin-extensions-backupsuite", d)} \
	dvb-usb-drivers-meta \
	cdfs cdtextinfo \
	"

DEPENDS += "${OPTIONAL_PACKAGES} ${ENIGMA2_OPTIONAL}"
#	channelsettings-enigma2-meta

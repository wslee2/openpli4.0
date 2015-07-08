MACHINE_KERNEL_PR_append = ".${INC_PR}.49"

COMPATIBLE_MACHINE = "dm[0-9]+.*"

PATCHREV = "4e0356d04e89df800361b9252f990716f5523c6e"
PATCHLEVEL = "60"

SRC_URI = " \
			${KERNELORG_MIRROR}/linux/kernel/v3.x/linux-3.2.tar.bz2;name=kernel \
			${KERNELORG_MIRROR}/linux/kernel/v3.x/patch-3.2.${PATCHLEVEL}.xz;apply=yes;name=kernel-patch \
			http://sources.dreamboxupdate.com/download/kernel-patches/${P}-${PATCHREV}.patch.bz2;name=dmm-patch \
			http://download.filesystems.org/unionfs/unionfs-2.x/unionfs-2.5.11_for_3.2.2.diff.gz;name=unionfs \
			file://clear_sublevel.patch \
			file://0001-Revert-MIPS-Fix-potencial-corruption.patch \
			file://fadvise_dontneed_change.patch \
			file://fix-proc-cputype.patch \
			file://mips-refactor-clearpage-and-copypage.patch \
			file://rtl8712-backport-b.patch \
			file://rtl8712-backport-c.patch \
			file://rtl8712-backport-d.patch \
			file://make-3.82-hack.patch \
			file://0001-SCSI-sd-Use-SCSI-read-write-16-with-32-bit-LBA-drive.patch \
			file://0002-add-crypto-api-xz-support.patch \
			file://0003-add-XZ-compression-support-to-UBIFS.patch \
			file://0004-block2mtd-add-possibility-to-change-the-writesize.patch \
			file://0005-block2mtd-add-possibility-to-remove-block2mtd-device.patch \
			file://0006-mtd-block2mtd-fix-recursive-call-of-mtd_writev.patch \
			file://0007-mtd-block2mtd-throttle-writes-by-calling-balance_dir.patch \
			file://0001-brmcnand_base-disable-flash-BBT-on-64MB-nand.patch \
			file://0002-ubifs-add-config-option-to-use-zlib-as-default-compr.patch \
			file://em28xx_fix_terratec_entries.patch \
			file://em28xx_add_terratec_h5_rev3.patch \
			file://dvb-usb-siano-always-load-smsdvb.patch \
			file://dvb-usb-af9035.patch \
			file://dvb-usb-a867.patch \
			file://dvb-usb-rtl2832.patch \
			file://dvb_usb_disable_rc_polling.patch \
			file://dvb-usb-smsdvb_fix_frontend.patch \
			file://0001-it913x-backport-changes-to-3.2-kernel.patch \
			file://0001-linuxtv-api-DMM-drivers-are-now-ready-for-linux-tv-a.patch \
			file://defconfig \
"

SRC_URI[kernel.md5sum] = "7ceb61f87c097fc17509844b71268935"
SRC_URI[kernel.sha256sum] = "c881fc2b53cf0da7ca4538aa44623a7de043a41f76fd5d0f51a31f6ed699d463"
SRC_URI[kernel-patch.md5sum] = "6281490378a62e987e5466514e893c9d"
SRC_URI[kernel-patch.sha256sum] = "35246da973ed05d60d8b48dc4ba12a3ad8d6abfd5ebcbe4902d7895c2b45250c"
SRC_URI[dmm-patch.md5sum] = "d17d65e9978343d540e0b60767a82286"
SRC_URI[dmm-patch.sha256sum] = "576356545de7f587d164d1cee2cb17b6c1ce3efbe2e01ff785c13ec2d544d220"
SRC_URI[unionfs.md5sum] = "06e7c9f6cafd49b72184be851116c511"
SRC_URI[unionfs.sha256sum] = "ce6ffa3c17a11dcca24196c11f6efc95c59b65a5b99958e73e8d4cc8e4b1f1ef"

S = "${WORKDIR}/linux-3.2"

require linux-dreambox.inc

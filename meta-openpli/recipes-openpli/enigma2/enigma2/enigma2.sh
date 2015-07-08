#!/bin/sh

prefix=/usr
exec_prefix=/usr
datarootdir=${prefix}/share

if [ -x /usr/bin/showiframe ]; then
	if [ -f /etc/enigma2/backdrop.mvi ]; then
		/usr/bin/showiframe /etc/enigma2/backdrop.mvi
	elif [ -f /etc/enigma2/bootlogo.mvi ]; then
		/usr/bin/showiframe /etc/enigma2/bootlogo.mvi
	elif [ -f /usr/share/bootlogo.mvi ]; then
		/usr/bin/showiframe /usr/share/bootlogo.mvi
	fi
fi

# hook to execute scripts always before enigma2 start
if [ -x /usr/bin/enigma2_pre_start.sh ]; then
	/usr/bin/enigma2_pre_start.sh
fi

if [ -d /home/root ]; then
	cd /home/root
fi

LIBS=/usr/lib/libopen.so.0.0.0

sync
LD_PRELOAD=$LIBS /usr/bin/enigma2

# enigma2 exit codes:
#
# 1 - halt
# 2 - reboot
# 3 - restart enigma
#
# >128 signal

ret=$?
case $ret in
	1)
		/sbin/halt
		;;
	2)
		/sbin/reboot
		;;
	3)
		rm -fR /home/root/.gstreamer-0.10
		rm /tmp/.listen.camd.socket.ignore
		;;
	4)
		/sbin/rmmod lcd
#		/usr/sbin/fpupgrade --upgrade 2>&1 | tee /home/root/fpupgrade.log
		sleep 1;
#		/sbin/rmmod fp
#		/sbin/modprobe fp
		/sbin/reboot
		;;
	5)
		if ! grep -q config.misc.RestartUI /etc/enigma2/settings; then
			echo "config.misc.RestartUI=true" >>/etc/enigma2/settings
		fi
		;;
	42)
		# bind the console (when available)
#		[ -f /sys/class/vtconsole/vtcon1/bind ] && echo 1 > /sys/class/vtconsole/vtcon1/bind
		opkg upgrade 2>&1 | tee /home/root/ipkgupgrade.log
		/sbin/reboot
		;;
	43)
		init 1
		;;
	64 | 65)		
		echo ** factory reset
		/usr/bin/enigma2_end.sh $ret
		/sbin/reboot
		;;
	90 | 91 | 92 | 93 | 94 | 95 | 96 | 97)
		echo ** update
		/usr/bin/enigma2_end.sh $ret
		;;
	*)
		rm /tmp/.listen.camd.socket.ignore
		;;
esac

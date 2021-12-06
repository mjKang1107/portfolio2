/* ****************
 *  일정 편집
 * ************** */
var editEvent = function (event, element, view) {

    $('#deleteEvent').data('id', event._id); //클릭한 이벤트 ID

    $('.popover.fade.top').remove();
    $(element).popover("hide");

    if (event.allDay === true) {
        editAllDay.prop('checked', true);
    } else {
        editAllDay.prop('checked', false);
    }

    if (event.end === null) {
        event.end = event.start;
    }

    if (event.allDay === true && event.end !== event.start) {
        editEnd.val(moment(event.end).subtract(1, 'days').format('YYYY-MM-DD HH:mm'))
    } else {
        editEnd.val(event.end.format('YYYY-MM-DD HH:mm'));
    }

    modalTitle.html('일정 수정');
    editTitle.val(event.title);
    editStart.val(event.start.format('YYYY-MM-DD HH:mm'));
    editType.val(event.type);
    editDesc.val(event.description);
    editColor.val(event.backgroundColor).css('color', event.backgroundColor);

    addBtnContainer.hide();
    modifyBtnContainer.show();
    eventModal.modal('show');

    //업데이트 버튼 클릭시
    $('#updateEvent').unbind();
    $('#updateEvent').on('click', function () {

        if (editStart.val() > editEnd.val()) {
            alert('끝나는 날짜가 앞설 수 없습니다.');
            return false;
        }

        if (editTitle.val() === '') {
            alert('일정명은 필수입니다.')
            return false;
        }

        var statusAllDay;
        var startDate;
        var endDate;
        var displayDate;

        if (editAllDay.is(':checked')) {
            statusAllDay = true;
            startDate = moment(editStart.val()).format('YYYY-MM-DD');
            endDate = moment(editEnd.val()).format('YYYY-MM-DD');
            displayDate = moment(editEnd.val()).add(1, 'days').format('YYYY-MM-DD');
        } else {
            statusAllDay = false;
            startDate = editStart.val();
            endDate = editEnd.val();
            displayDate = endDate;
        }

        eventModal.modal('hide');

        event.allDay = statusAllDay;
        event.title = editTitle.val();
        event.start = startDate;
        event.end = displayDate;
        event.type = editType.val();
        event.backgroundColor = editColor.val();
        event.description = editDesc.val();

        //일정 업데이트
        $.ajax({
            type: "get",
            url: "./updateSchedule.tm",
            data: {
            	idx: event._id,
            	title: event.title,
                description: event.description,
                start: event.start,
                end: event.end,
                type: event.type,
                backgroundColor: event.backgroundColor,
                allDay: event.allDay
            },
            success: function (response) {
            	if(response == 1) {
            		alert('수정이 완료되었습니다.');     
            		$("#calendar").fullCalendar('updateEvent', event);
            	} else if (response == 0) {
            		alert('본인 일정이 아닙니다.');
            		location.reload();
            	} else {
            		alert('일정변경에 문제가 생겼습니다.');
            		location.reload();
            	}
            }
        });
    });
};

// 삭제버튼
$('#deleteEvent').on('click', function () {
    	
    $('#deleteEvent').unbind();
    eventModal.modal('hide');

    var idx = $(this).data('id');
    
    //삭제시
    $.ajax({
        type: "get",
        url: "./deleteSchedule.tm",
        data: {
        	idx: $(this).data('id')
        },
        success: function (response) {
            if(response == 1) {
            	alert('삭제되었습니다.');
            	$("#calendar").fullCalendar('removeEvents', idx);
        	} else if (response == 0) {
        		alert('본인 일정이 아닙니다.');
        		location.reload();
        	} else {
        		alert('일정변경에 문제가 생겼습니다.');
        		location.reload();
        	}
        }
    });
});
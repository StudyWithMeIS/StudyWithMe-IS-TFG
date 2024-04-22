function generateCalendar(month, year) {
    const daysContainer = document.querySelector('.days-container');
    daysContainer.innerHTML = '';

    const firstDayOfMonth = new Date(year, month, 1);
    const lastDayOfMonth = new Date(year, month + 1, 0);
    const startingDayOfWeek = firstDayOfMonth.getDay();

    const daysInMonth = lastDayOfMonth.getDate();

    const numOfWeeks = Math.ceil((daysInMonth + startingDayOfWeek) / 7);

    let dayCounter = 1;

    for (let i = 0; i < numOfWeeks; i++) {
        const row = document.createElement('div');
        row.classList.add('row');

        for (let j = 0; j < 7; j++) {
            const day = document.createElement('div');
            day.classList.add('col', 'day', 'text-center');

            if ((i === 0 && j < startingDayOfWeek) || dayCounter > daysInMonth) {
                day.innerText = '';
            } else {
                day.innerText = dayCounter;
                if (new Date().toDateString() === new Date(year, month, dayCounter).toDateString()) {
                    day.classList.add('today');
                }
                dayCounter++;
            }

            row.appendChild(day);
        }

        daysContainer.appendChild(row);
    }
}

document.addEventListener('DOMContentLoaded', function () {
    const now = new Date();
    const month = now.getMonth();
    const year = now.getFullYear();
    document.getElementById('month-year').innerText = new Date(year, month).toLocaleString('default', { month: 'long' }) + ' ' + year;
    generateCalendar(month, year);
});
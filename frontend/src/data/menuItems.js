export const menuItems = [
  {
    title: '소개',
    submenu: [
      {
        title: '인사말',
        url: '/intro',
      },
      {
        title: '미션&비전',
        url: '/vission',
      },
      {
        title: '통계',
        url: '/statistics',
      },
    ],
  },
  {
    title: '보호소',
    url: '/shelter/search',
  },
  {
    title: '신고',
    submenu: [
      {
        title: '실종 신고',
        url: '/report/miss',
      },
      {
        title: '임보 신고',
        url: '/report/temp',
      },
    ],
  },
  {
    title: '봉사',
    url: '/volunteer/search',
  },
  {
    title: '커뮤니티',
    submenu: [
      {
        title: '입양 후기',
        url: '/adoption-reviews',
      },
      {
        title: '정보 공유',
        url: '/infos',
      },
      {
        title: '용품 무료 나눔',
        url: '/free-shares',
      },
      {
        title: '봉사 모집 공고',
        url: '/volu-notices',
      },
    ],
  },
];
